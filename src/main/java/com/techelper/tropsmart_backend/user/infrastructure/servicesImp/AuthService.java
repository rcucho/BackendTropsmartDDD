package com.techelper.tropsmart_backend.user.infrastructure.servicesImp;

import com.techelper.tropsmart_backend.cargo.domain.models.ServiceRequest;
import com.techelper.tropsmart_backend.cargo.domain.repositories.IServiceRequestRepository;
import com.techelper.tropsmart_backend.configuration.application.exception.ResourceNotFoundException;
import com.techelper.tropsmart_backend.configuration.domain.security.JwtProvider;
import com.techelper.tropsmart_backend.customer.domain.models.Customer;
import com.techelper.tropsmart_backend.customer.domain.repositories.ICustomerRepository;
import com.techelper.tropsmart_backend.driver.domain.models.Driver;
import com.techelper.tropsmart_backend.driver.domain.models.Qualification;
import com.techelper.tropsmart_backend.driver.domain.repositories.IDriverRepository;
import com.techelper.tropsmart_backend.driver.domain.repositories.IQualificationRepository;
import com.techelper.tropsmart_backend.user.domain.models.Balance;
import com.techelper.tropsmart_backend.user.domain.models.Person;
import com.techelper.tropsmart_backend.user.domain.models.User;
import com.techelper.tropsmart_backend.user.domain.repositories.IBalanceRepository;
import com.techelper.tropsmart_backend.user.domain.repositories.IPersonRepository;
import com.techelper.tropsmart_backend.user.domain.repositories.IUserRepository;
import com.techelper.tropsmart_backend.user.domain.services.IAuthService;
import com.techelper.tropsmart_backend.user.domain.services.comunications.AuthResponse;
import com.techelper.tropsmart_backend.user.domain.services.inputs.SignUp;
import com.techelper.tropsmart_backend.user.domain.services.outputs.AuthenticatedOutput;
import com.techelper.tropsmart_backend.userconfiguration.domain.models.Configuration;
import com.techelper.tropsmart_backend.userconfiguration.domain.repositories.IConfigurationRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.validator.cfg.GenericConstraintDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IDriverRepository driverRepository;

    @Autowired
    private IQualificationRepository qualificationRepository;

    @Autowired
    private IServiceRequestRepository serviceRequestRepository;

    @Autowired
    private IConfigurationRepository configurationRepository;

    @Autowired
    private IBalanceRepository balanceRepository;

    //@Autowired
    //private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    //@Autowired
    //private IRefreshTokenService refreshTokenService;

    //@Autowired
    //private PasswordEncoder encoder;



    @Override
    public AuthResponse registerComplete(SignUp signUp) {
        try
        {
            //NestedFactory nestedFactory = new NestedFactory();
            //User user = (User)(nestedFactory.create(signUp.getDiscriminator(), signUp));


            Person newPerson = new Person();
            newPerson.setFirstName(signUp.getFirstName());
            newPerson.setLastName(signUp.getLastName());
            newPerson.setPhone(signUp.getPhone());
            newPerson.setPersonType(signUp.getDiscriminator());
            newPerson = personRepository.save(newPerson);



            Configuration newConfiguration = new Configuration();
            newConfiguration.setLanguage("Spanish");
            newConfiguration.setPaymentCurrency("Soles");

            newConfiguration = configurationRepository.save(newConfiguration);

            Balance newBalance = new Balance();
            newBalance.setSpentMoney(0);
            newBalance.setAddedMoney(0);

            newBalance = balanceRepository.save(newBalance);


            User user = new User();
            user.setEmail(signUp.getEmail());
            user.setPerson(newPerson);

            //user.setPassword(encoder.encode((signUp.getPassword())));
            user.setPassword(signUp.getPassword());
            user.setCreatedAt(Calendar.getInstance().getTime());
            user.setConfiguration(newConfiguration);
            user.setBalance(newBalance);

            user = userRepository.save(user);


            if(signUp.getDiscriminator() == 1) {
                Customer newCustomer = new Customer();
                newCustomer.setCredits(0.0);
                newCustomer.setPerson(newPerson);
                newPerson.setCustomer(newCustomer);
                customerRepository.save(newCustomer);
            }
            else
            {
                Driver newDriver = new Driver();
                newDriver.setLicense("000-123");
                newDriver.setPerson(newPerson);
                newPerson.setDriver(newDriver);

                Qualification newQualication = new Qualification();
                newQualication.setDriver(newDriver);

                ServiceRequest newServiceRequest = new ServiceRequest();
                newServiceRequest.setDriver(newDriver);

                qualificationRepository.save(newQualication);
                serviceRequestRepository.save(newServiceRequest);
                driverRepository.save(newDriver);
            }

            return new AuthResponse(new AuthenticatedOutput(user.getId(),user.getEmail(),user.getPassword(),signUp.getFirstName(),signUp.getLastName(),signUp.getDiscriminator()));
        }
        catch (Exception e)
        {
            return new AuthResponse("An error ocurred while register the user: "+e.getMessage());
        }
    }

    @Override
    public AuthResponse login(String email, String password) {
        try {
            User getUser = userRepository.findByEmail(email)
                    .orElseThrow(()->new ResourceNotFoundException("user","email",email));
            Person getPerson = getUser.getPerson();


            //Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            //SecurityContextHolder.getContext().setAuthentication(authentication);

            AuthenticatedOutput authenticatedOutput =new AuthenticatedOutput(getUser.getId(),
                    getUser.getEmail(),
                    getUser.getPassword(),
                    getPerson.getFirstName(),
                    getPerson.getLastName(),
                    getPerson.getPersonType());

            if(getPerson.getPersonType()==1)
                authenticatedOutput.setRoleId(getPerson.getCustomer().getId());
            if(getPerson.getPersonType()==2)
                authenticatedOutput.setRoleId(getPerson.getDriver().getId());

            //String jwt = jwtProvider.generateJwtToken(authentication);
            //authenticatedOutput.setJwt(jwt);
            //authenticatedOutput.setRefreshToken(jwtProvider.generateRefreshToken(getUser, jwt, refreshTokenService));


            String secretKey = "mySecretKey";
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_USER");

            String token = Jwts
                    .builder()
                    .setId("softtekJWT")
                    .setSubject(email)
                    .claim("authorities",
                            grantedAuthorities.stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 600000))
                    .signWith(SignatureAlgorithm.HS512,
                            secretKey.getBytes()).compact();

            String r = "Bearer "+token;
            authenticatedOutput.setToken(r);
            return new AuthResponse(authenticatedOutput);
        }
        catch (Exception e)
        {
            return new AuthResponse("An error ocurred while getting the user: "+e.getMessage());
        }
    }

    /*
    @Override
    public AuthResponse refresh(RefreshInput refreshInput) throws Exception {
        RefreshToken refreshToken = refreshTokenService.findById(refreshInput.getRefreshToken()).get();
        String tokenId = jwtProvider.getJwtTokenId(refreshInput.getToken());

        if (refreshToken.getToken().equals(tokenId) && jwtProvider.validateRefreshToken(refreshToken)) {
            User user = refreshToken.getUser();
            refreshToken.setState(false);
            refreshTokenService.save(refreshToken);
            return login(user.getEmail(), user.getPassword());
        }
        return new AuthResponse("Can't validate token");
    }
     */
}