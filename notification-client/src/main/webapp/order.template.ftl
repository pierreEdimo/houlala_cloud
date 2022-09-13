<html lang="fr" xmlns="http://www.w3.org/1999/xhtml">
   <head>
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Poppins" rel="stylesheet">
       <title>Houlala</title>
   </head>
   <body class="email-container">
       <section class="order-container">
           <div class="client-container">
               <p>${userInformation.lastName} ${userInformation.firstName}</p>
               <p>${userInformation.streetName}, ${userInformation.houseNumber}</p>
               <p>${userInformation.poBox}, ${userInformation.city}</p>
               <p>${userInformation.country}</p>
           </div>
           <div style="height: 30px"></div>
           <div class="item-container">
              <#list order.cartItems as item>
                  <div class="item-content-container">
                     <div class="img" >
                        <img alt="product-image" src="${item.imageUrl}" class="product"/>
                     </div>
                     <div style="width: 30px"></div>
                     <div class="name-content">
                        <h3>${item.product}</h3>
                        <div style="height: 5px"></div>
                        <p>${item.price} FCFA</p>
                        <div style="height: 5px"></div>
                        <p>${item.quantity}</p>
                     </div>
                  </div>
              </#list>
           </div>
           <div style="height: 20px"></div>
           <div class="item-container-bottom">
                  <P>Total: ${order.totalQuantity}</p>
                  <p>Prix: ${order.totalPrice} FCFA</p>
              </div>
       </section>
   </body>
</html>