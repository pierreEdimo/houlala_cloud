package com.example.authenticationclient.feign;

import com.example.authenticationclient.exception.AuthenticationException;
import com.example.authenticationclient.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "https://authentication.houlala.store/api", name = "authentication")
public interface AuthenticationFeignClient {


    /**
     * Enregistre un nouvel utilisateur dans la base de donnees
     *
     * @param model Les informations de l'utilisateur.
     * @return Un nouvel utilisateur en tant que JSON.
     * @throws AuthenticationException En Cas d'erreur.2
     */
    @PostMapping("/Auth/Register")
    UserToken register(@RequestBody Register model) throws AuthenticationException;

    /**
     * Permet a un utilisateur de se connecter a son compte.
     *
     * @param model L'adresse E-mail et le mot de passe de l'utilisateur.
     * @return Le token de l'utilisateur.
     * @throws AuthenticationException En cas d'erreur.
     */
    @PostMapping("/Auth/Login")
    UserToken login(@RequestBody Login model) throws AuthenticationException;

    /**
     * Cherche la liste des Utilisateurs enregistres de la base de donnees.
     *
     * @param auth Le Token de l'utilisateur dans le header.
     * @return La liste de tous les utilisateurs enregistres dans la base donnee.
     * @throws AuthenticationException En cas d'erreur.
     */
    @GetMapping("/Auth/GetAllUsers")
    List<UserDto> getAllUsers(@RequestHeader("Authorization") String auth) throws AuthenticationException;

    /**
     * Permet a un utilisateur de changer son mot de passe.
     *
     * @param model Le mot de passe de l'utilisateur, et son adresse E-mail.
     * @return L'utilisateur ainsi que son nouveau mot de passe.
     * @throws AuthenticationException En cas d'erreur
     */
    @PostMapping("/Auth/renewPassWord")
    UserToken renewPassWord(@RequestBody Login model) throws AuthenticationException;

    /**
     * Permet de chercher les informations d'un utilisateur a partir de son E-mail.
     *
     * @param email L'E-mail adresse de l'Utilisateur.
     * @param auth  Le Token de l'utilisateur dans le header.
     * @return Les informations personnelles de l'utilisateur.
     * @throws AuthenticationException En cas d'erreur.
     */
    @GetMapping("/Auth/GetUserByEmail/{Email}")
    UserDto getSingleUserByEmail(@PathVariable("Email") String email, @RequestHeader("Authorization") String auth) throws AuthenticationException;

    /**
     * Permet de modifier les informations de l'utilisateur.
     *
     * @param model Les Informations de l'utilisateur qui doivent etre changees.
     * @param email L'adresse E-mail de l'utilisateur.
     * @param auth  Le Token de l'utilisateur dans le header.
     * @return L'utilisateur avec ses nouvelles informations modifiees.
     * @throws AuthenticationException En cas d'erreur
     */
    @PutMapping("/Auth/editUserInformations/{Email}")
    UserToken editPersonalData(@RequestBody PersonalData model, @PathVariable("Email") String email, @RequestHeader("Authorization") String auth) throws AuthenticationException;

    /**
     * Permet de modifier l'adresse de livraison de l'utilisateur.
     *
     * @param model L'ancienne adresse de l'utilisateur.
     * @param email L'adresse E-mail de l'utilisateur.
     * @param auth  Le Token de l'utilisateur dans le header.
     * @return La nouvelle adresse de l'utilisateur.
     * @throws AuthenticationException En cas d'erreur.
     */
    @PutMapping("/Auth/editAddressInformations/{Email}")
    UserToken editAddressInfos(@RequestBody AddressData model, @PathVariable("Email") String email, @RequestHeader("Authorization") String auth) throws AuthenticationException;

    /**
     * Permet de modifier l'adresse E-mail de l'utilisateur.
     *
     * @param model l'ancienne E-mail de l'utilisateur
     * @param email La nouvelle E-mail de l'utilisateur
     * @param auth  Le Token de l'utilisateur dans le header.
     * @return La nouvelle adresse E-mail de l'utilisateur.
     * @throws AuthenticationException En cas d'erreur-
     */
    @PutMapping("/Auth/editUserEmail/{Email}")
    UserToken editEmail(@RequestBody EditEmail model, @PathVariable("Email") String email, @RequestHeader("Authorization") String auth) throws AuthenticationException;

    /**
     * Permet de modifier les informations du vendeur
     *
     * @param info Les informations du Vendeur.
     * @param auth Le Token de l'utilisateur dans le header.
     * @return La nouveau userName et l'Email.
     * @throws AuthenticationException En cas d'erreur.
     */
    @PutMapping("Auth/editSellerInfo")
    UserToken editSellerInfo(@RequestBody SellerInfo info, @RequestHeader("Authorization") String auth) throws AuthenticationException;
}
