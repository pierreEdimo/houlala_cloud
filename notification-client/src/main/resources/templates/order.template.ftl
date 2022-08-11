<html lang="fr" xmlns="http://www.w3.org/1999/xhtml">
   <head>
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Poppins" rel="stylesheet">
      <style>
        p{
         margin: 0; 
         padding: 0; 
         font-size: 18px; 
        }
       .email-container {
         background-color: #fff; 
         padding: 50px; 
         font-family: 'Poppins', sans-serif;
         }
       .order-container{
         width: 700px; 
         margin: auto; 
         padding: 10px; 
         }
         .item-container{
            width: 100%; 
            padding: 10px; 
            border: 1px solid #DCDCDC; 
            border-radius: 10px; 
         }
         .item-content-container{
            display: flex; 
            margin-bottom: 20px; 
         }
         .img{
            width: 40%; 
         }
         .product{
            width: 100%; 
            height: 270px;
            border-radius: 10px; 
         }
         .item-container-bottom{
             display: flex; 
             justify-content: space-between; 
         }
         h3{
            font-weight: bolder; 
            font-size: 22px; 
            margin: 0; 
         }
      </style>
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