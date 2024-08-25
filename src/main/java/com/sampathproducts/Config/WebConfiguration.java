package com.sampathproducts.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfiguration {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/login").permitAll()
                    .requestMatchers("/error").permitAll()
                    .requestMatchers("/index").permitAll()
                    .requestMatchers("/resources").permitAll()
                    .requestMatchers("/createadmin").permitAll()
                    /*
                     * .requestMatchers("/aeraAdd").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/area/**").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/areadeleted").hasAnyRole("Admin", "Manager", "Sales_rep")
                     * .requestMatchers("/areas").hasAnyRole("Admin", "Manager", "Sales_rep")
                     * .requestMatchers("/areas/findall").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/areas/findall/deleted").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/areas/findall/exist").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/customer").hasAnyRole("Admin", "Manager", "Sales_rep")
                     * .requestMatchers("/customer/delete").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/customer/edit").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/customer/findall").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/customer/findall/deleted").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/customer/findall/exist").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/customer/restore").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/customer/save").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/customeradd").hasAnyRole("Admin", "Manager", "Sales_rep")
                     * .requestMatchers("/customerdeleted").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/customeredit").hasAnyRole("Admin", "Manager", "Sales_rep")
                     * .requestMatchers("/customerorder").hasAnyRole("Admin", "Manager",
                     * "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/customerorder/edit").denyAll()
                     * .requestMatchers("/customerorder/findall/ending")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/customerorder/findall/expired")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/customerorder/findall/ordered")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/customerorder/findall/valid")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/customerorder/save").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/customerorderadd").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/customerorders/findall")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/customerview").hasAnyRole("Admin", "Manager", "Sales_rep")
                     * .requestMatchers("/custorderproduct/findall")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/dashboards/manager").hasAnyRole("Manager")
                     * .requestMatchers("/dashboards/admin").hasAnyRole("Admin")
                     * .requestMatchers("/dashboards/salesrep").hasAnyRole("Sales_rep")
                     * .requestMatchers("/dashboards/storekeeper").hasAnyRole("Stock_keeper")
                     * .requestMatchers("/division/findall").hasAnyRole("Admin")
                     * .requestMatchers("/division/save").hasAnyRole("Admin")
                     * .requestMatchers("/employee").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/employee/delete").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/employee/edit").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/employee/findall").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/employee/findall/deleted").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/employee/findall/exist").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/employee/findall/nouser").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/employee/restore").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/employee/save").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/employeeadd").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/employeedeleted").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/employeeedit").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/employeeview").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/endingcustomerorder").hasAnyRole("Admin", "Manager",
                     * "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/endingquot").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/expiredcustomerorder")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/expiredquot").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/FormView").denyAll()
                     * .requestMatchers("/home").permitAll()
                     * .requestMatchers("/lastemployee").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/material").hasAnyRole("Admin", "Manager", "Stock_keeper")
                     * .requestMatchers("/material/delete").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/material/edit").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/material/findall/deleted").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/material/findall/exist").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/material/restore").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/material/save").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/materialadd").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/materialdeleted").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/materialneed").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/materialordered").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/materialreceived").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/materials/findall").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/module/edit").hasAnyRole("Admin")
                     * .requestMatchers("/module/findall").hasAnyRole("Admin")
                     * .requestMatchers("/module/save").hasAnyRole("Admin")
                     * .requestMatchers("/modulerole/edit").hasAnyRole("Admin")
                     * .requestMatchers("/payment/delete").denyAll()
                     * .requestMatchers("/payment/restore").denyAll()
                     * .requestMatchers("/payment/save").hasAnyRole("Admin", "Manager", "Sales_rep")
                     * .requestMatchers("/paymentAdd").hasAnyRole("Admin", "Manager", "Sales_rep")
                     * .requestMatchers("/paymentdeleted").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/payments").hasAnyRole("Admin", "Manager", "Sales_rep")
                     * .requestMatchers("/payments/findall").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/payments/findall/deleted").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/payments/findall/exist").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/privilage").hasAnyRole("Admin")
                     * .requestMatchers("/privilage/findall").hasAnyRole("Admin")
                     * .requestMatchers("/privilageAdd").hasAnyRole("Admin")
                     * .requestMatchers("/privilagedeleted").hasAnyRole("Admin")
                     * .requestMatchers("/product").hasAnyRole("Admin", "Manager", "Sales_rep",
                     * "Stock_keeper")
                     * .requestMatchers("/product/delete").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/product/edit").hasAnyRole("Admin", "Manager", "Sales_rep",
                     * "Stock_keeper")
                     * .requestMatchers("/product/findall").hasAnyRole("Admin", "Manager",
                     * "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/product/findall/deleted")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/product/findall/exist")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/product/restore").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/product/save").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/productadd").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/productdeleted").hasAnyRole("Admin", "Manager",
                     * "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/productedit").hasAnyRole("Admin", "Manager", "Sales_rep",
                     * "Stock_keeper")
                     * .requestMatchers("/productmaterial/findall")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/productsize/delete").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/productsize/edit").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/productsize/restore").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/productsize/save").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/productsizeAdd").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/productsizedeleted").hasAnyRole("Admin", "Manager",
                     * "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/productsizes").hasAnyRole("Admin", "Manager", "Sales_rep",
                     * "Stock_keeper")
                     * .requestMatchers("/productsizes/findall")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/productsizes/findall/deleted")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/productsizes/findall/exist")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/producttype/delete").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/producttype/edit").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/producttype/restore").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/producttype/save").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/producttypeAdd").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/producttypedeleted").hasAnyRole("Admin", "Manager",
                     * "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/producttypes").hasAnyRole("Admin", "Manager", "Sales_rep",
                     * "Stock_keeper")
                     * .requestMatchers("/producttypes/findall")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/producttypes/findall/deleted")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/producttypes/findall/exist")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/productview").hasAnyRole("Admin", "Manager", "Sales_rep",
                     * "Stock_keeper")
                     * .requestMatchers("/purchaseorder").hasAnyRole("Admin", "Manager",
                     * "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/purchaseorder/findall")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/purchaseorder/findall/deleted")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/purchaseorder/findall/exist")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/purchaseorder/save").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/purchaseorderadd").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/purchaseorderdeleted")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/purchaseorderedit").denyAll()
                     * .requestMatchers("/purchaseorderLine").hasAnyRole("Admin", "Manager",
                     * "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/purchaseorderLine/edit").denyAll()
                     * .requestMatchers("/purchaseorderLine/findall")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/purchaseorderview").hasAnyRole("Admin", "Manager",
                     * "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/quotation").hasAnyRole("Admin", "Manager", "Stock_keeper")
                     * .requestMatchers("/recievenote").hasAnyRole("Admin", "Manager", "Sales_rep",
                     * "Stock_keeper")
                     * .requestMatchers("/recievenote/findall").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/recievenote/save").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/recievenoteadd").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/recievenotedeleted").denyAll()
                     * .requestMatchers("/recievenoteedit").denyAll()
                     * .requestMatchers("/recievenoteview").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/request/edit").denyAll()
                     * .requestMatchers("/request/findall/created").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/request/findall/ending").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/request/findall/expired").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/request/findall/requested").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/request/findall/valid").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/request/save").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/requestadd").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/requestedquot").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/requests/findall").hasAnyRole("Admin", "Manager",
                     * "Stock_keeper")
                     * .requestMatchers("/role/findall").hasAnyRole("Admin", "Manager", "Sales_rep",
                     * "Stock_keeper")
                     * .requestMatchers("/role/save").hasAnyRole("Admin")
                     * .requestMatchers("/supplier").hasAnyRole("Admin", "Manager", "Sales_rep",
                     * "Stock_keeper")
                     * .requestMatchers("/supplier/delete").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/supplier/edit").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/supplier/findall").hasAnyRole("Admin", "Manager",
                     * "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/supplier/findall/deleted")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/supplier/findall/exist")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/supplier/findall/quotation")
                     * .hasAnyRole("Admin", "Manager", "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/supplier/restore").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/supplier/save").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/supplieradd").hasAnyRole("Admin", "Manager", "Sales_rep")
                     * .requestMatchers("/supplierdeleted").hasAnyRole("Admin", "Manager",
                     * "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/supplieredit").hasAnyRole("Admin", "Manager", "Sales_rep")
                     * .requestMatchers("/supplierview").hasAnyRole("Admin", "Manager", "Sales_rep",
                     * "Stock_keeper")
                     * .requestMatchers("/TableView").denyAll()
                     * .requestMatchers("/types/findall").hasAnyRole("Admin")
                     * .requestMatchers("/types/save").hasAnyRole("Admin")
                     * .requestMatchers("/user").hasAnyRole("Admin")
                     * .requestMatchers("/user/delete").hasAnyRole("Admin")
                     * .requestMatchers("/user/edit").hasAnyRole("Admin")
                     * .requestMatchers("/user/findall").hasAnyRole("Admin")
                     * .requestMatchers("/user/findall/deleted").hasAnyRole("Admin")
                     * .requestMatchers("/user/findall/exist").hasAnyRole("Admin")
                     * .requestMatchers("/user/restore").hasAnyRole("Admin")
                     * .requestMatchers("/user/save").hasAnyRole("Admin")
                     * .requestMatchers("/useradd").hasAnyRole("Admin")
                     * .requestMatchers("/userdeleted").hasAnyRole("Admin")
                     * .requestMatchers("/useredit").hasAnyRole("Admin")
                     * .requestMatchers("/userview").hasAnyRole("Admin")
                     * .requestMatchers("/validcustomerorder").hasAnyRole("Admin", "Manager",
                     * "Sales_rep", "Stock_keeper")
                     * .requestMatchers("/validquot").hasAnyRole("Admin", "Manager", "Stock_keeper")
                     * .requestMatchers("/vehicle").hasAnyRole("Admin", "Manager", "Sales_rep")
                     * .requestMatchers("/vehicle/delete").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/vehicle/edit").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/vehicle/findall").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/vehicle/findall/deleted").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/vehicle/findall/exist").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/vehicle/restore").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/vehicle/save").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/vehicleadd").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/vehicledeleted").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/vehicleedit").hasAnyRole("Admin", "Manager")
                     * .requestMatchers("/vehicletypes/findall").hasAnyRole("Admin", "Manager",
                     * "Sales_rep")
                     * .requestMatchers("/vehicleview").hasAnyRole("Admin", "Manager", "Sales_rep")
                     */
                    .anyRequest().authenticated();
        })
                .formLogin(login -> {
                    login.loginPage("/login")
                            .defaultSuccessUrl("/index", true)
                            .failureUrl("/login?error=usernamepassworderror")
                            .usernameParameter("user_name")
                            .passwordParameter("user_password");
                })
                .logout(logout -> {
                    logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/login");

                })
                .exceptionHandling(exception -> {
                    exception.accessDeniedPage("/error");
                })
                .csrf(csrf -> {
                    csrf.disable();
                });

        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
