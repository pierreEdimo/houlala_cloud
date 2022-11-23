<html lang="fr" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins" rel="stylesheet">
    <title>Houlala</title>
</head>

<body>

<div class="email-container">
    <section class="center">
        <h1>Houlala</h1>
    </section>
    <hr>
    <section class="bestaetigung-container center">
        <br>
        <br>
        <span class="title">
                Nouvelle Commande
            </span>
        <p>
            Chère (cher) commerçant(e) <br>

            Nous vous annonçons avec joie que vous avez reçu une commande venant de ${userInformation.lastName}
            ${userInformation.firstName}.

            Le Paiement se fait en cash.
        </p>
        <br>
        <br>
    </section>
    <hr>
    <section class="adress-container center">
        <br>
        <br>
        <div class="client-container">
                <span class="small-title">
                    Adresse de livraison
                </span>
            <p>${userInformation.lastName} ${userInformation.firstName}</p>
            <p>${userInformation.phoneNumber}</p>
            <p>${userInformation.streetName},</p>
            <p>${userInformation.country}</p>
        </div>
        <br>
        <br>

    </section>
    <hr>
    <section class="product-container center">
        <br>
        <br>
        <div class="item-container">
            <#list order.cartItems as item>
                <br>
                <br>
                <div class="item-content-container">
                    <div class="img">
                        <img alt="product-image"
                             src="${item.imageUrl}"
                             class="product-image"/>
                    </div>
                    <br>
                    <div class="name-content">
                        <span class="small-title">${item.product}</span>
                        <div style="height: 5px"></div>
                        <p> ${item.price} FCFA</p>
                        <div style="height: 5px"></div>
                        <p>${item.quantity}</p>
                    </div>
                </div>
                <br>
                <br>
            </#list>
        </div>
        <br>
        <br>
    </section>
    <hr>
    <section class="total-container center">
        <br>
        <br>
        <div class="item-container-bottom">
            <P><b>Total:</b> ${order.totalQuantity}</p>
            <p><b>Prix:</b> ${order.totalPrice} FCFA</p>
        </div>
        <br>
        <br>
    </section>
    <hr>
    <section class="right center">
        <p>Pour toute question, veuillez contacter l'acheteur via son numero: ${userInformation.phoneNumber}</p>
        <p><b>Contact de Houlala:</b> +237 6 97 59 04 70</p>
    </section>
</div>

</body>

<style>
    body {
        font-family: 'Poppins', sans-serif;
        background-color: #ffffff;
    }

    h1 {
        font-size: 32px;
    }

    .title {
        font-weight: bolder;
        font-size: 22px;
    }

    .small-title {
        font-weight: bolder;
        font-size: 18px;
    }

    p {
        margin: 0;
        font-size: 14px;
    }

    .email-container {
        padding: 20px;
        width: 40%;
        margin: auto;
    }

    .center {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .item-content-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    .product-image {
        width: 240px;
    }

    p {
        text-align: center;
    }
</style>

</html>