/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.config;

import com.iicesv.controller.LoginSecurity;
//import com.smartfix.controller.StatisticsController;
import com.iicesv.entities.IiceMenuPrincipalUsersV;
import com.iicesv.entities.IiceSubMenuUsersV;
import com.iicesv.entities.IiceUsuarios;
import com.iicesv.services.ISmfMenuPrincipalUsersVServices;
import com.iicesv.services.IIicesvRolServices;
import com.iicesv.services.ISmfRolUsuarioServices;
import com.iicesv.services.ISmfSubMenuUsersVServices;
import com.iicesv.services.IUsuariosServices;
import com.iicesv.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.BaseMenuModel;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 *
 * @author Denisse
 */
public class AuthenticationProviderSec extends Utils implements AuthenticationProvider {

    
   
    
    public AuthenticationProviderSec() {
        super();
    }
    
    

    @Autowired
    IUsuariosServices usuarioService;

    @Autowired
    ISmfRolUsuarioServices iSmfRolUsuarioServices;

    @Autowired
    IIicesvRolServices iSmfRolServices;

    @Autowired
    SessionRegistry sessionRegistry;

    @Autowired
    ISmfMenuPrincipalUsersVServices iSmfMenuPrincipalUsersVServices;

    @Autowired
    ISmfSubMenuUsersVServices iSmfSubMenuUsersVServices;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        BCryptPasswordEncoder pass = new BCryptPasswordEncoder(12);
        MenuModel modelRender = new DefaultMenuModel();
        Authentication authRet = null;
        int idRolUsuario;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        LoginSecurity sec = (LoginSecurity) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "loginSecurity");
       
        try {

            List<GrantedAuthority> roles = new ArrayList<>();
            GrantedAuthority role = null;
            String usuario = auth.getPrincipal().toString().trim();
            IiceUsuarios user = usuarioService.getUsuariosValido(usuario);
            String password = auth.getCredentials().toString().trim();
            setCurrentUserInit(usuario);           
            if (usuario.isEmpty() && password.isEmpty()) {

                throw new BadCredentialsException("Usuario y Contraseña con requeridos");

            } else {

                if (user != null) {

                    if (pass.matches(password, user.getPassword())) {

                            idRolUsuario = iSmfRolUsuarioServices.obtenerUsuarioROl(user.getId()).getIdRol();
                            
                            sec.setModel(new BaseMenuModel());
                           
                            
                            List<IiceMenuPrincipalUsersV> menuPpal = iSmfMenuPrincipalUsersVServices.obtenerMenuPrincipal(user.getUsr(), idRolUsuario);
                             DefaultSubMenu firstSubmenuPrincipal = new DefaultSubMenu();
                             firstSubmenuPrincipal.setLabel("");
                             
                            menuPpal.stream().map((ppal) -> {
                                DefaultSubMenu firstSubmenu = new DefaultSubMenu();
                                firstSubmenu.setLabel(ppal.getDescripcion());
                                firstSubmenu.setIcon("fa fa-fw " + ppal.getMenuIcon());
                                List<IiceSubMenuUsersV> subMenu = iSmfSubMenuUsersVServices.obtenerSubMenu(ppal.getId(), user.getUsr(), idRolUsuario);
                                subMenu.stream().map((sub) -> DefaultMenuItem.builder().value(sub.getDescripcion()).outcome(sub.getUrl()).icon("fa fa-fw " + sub.getMenuIcon()).build()).forEachOrdered((item) -> {
                                    firstSubmenu.getElements().add(item);
                                });
                            return firstSubmenu;
                        }).forEachOrdered((firstSubmenu) -> {
                            //modelRender.getElements().add(firstSubmenu);
                            firstSubmenuPrincipal.getElements().add(firstSubmenu);
                        });
                            modelRender.getElements().add(firstSubmenuPrincipal);
                            DefaultMenuItem item = new DefaultMenuItem();
                            item.setValue("Cerrar sesión");
                            item.setIcon("fa fa-fw fa-user-times");
                            item.setAjax(false);
                            item.setCommand("#{loginSecurity.logOut()}");
                            modelRender.getElements().add(item);
                            sec.setModel(modelRender);
                            role = new SimpleGrantedAuthority(iSmfRolServices.obtenerRolById(idRolUsuario).getDescripcion());
                            roles.add(role);
                            authRet = new UsernamePasswordAuthenticationToken(usuario, password, roles);

                        } else {
                        throw new BadCredentialsException("Usuario o Contraseña Invalida");
                    }

                } else {

                    throw new BadCredentialsException("Usuario o Contraseña Invalida");
                }

            }

        } catch (BadCredentialsException e) {

            throw new BadCredentialsException("Usuario o Contraseña Invalida");

        }
        return authRet;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}
