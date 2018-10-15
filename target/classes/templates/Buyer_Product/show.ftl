<html>
<#include "../partials/_header.ftl">
<body>
<div class="container  ">
    <#include "../partials/_nav.ftl">
    <h1 align="center" class="display-4 mb-5">Product Detail</h1>
    <div class="row text-center justify-content-center">
        <div class="col-lg-6 ">
            <div class="card mb-4 ">
                <img height="50%" class="card-img-top" src="${productInfo.getProductIcon()}">
                <div class="card-body">
                    <h4 class="card-title ">${productInfo.getProductName()}</h4>
                    <form method="post" action="/sell/buyer/cart/save">
                        <div class="text-left">
                            <input hidden name="productId" value="${productInfo.getProductId()}">
                            <p class="card-test"><strong>Description: </strong>${productInfo.getProductDescription()}
                            </p>
                            <p class="card-text">
                                <strong>Price: </strong>
                                <label id="price"
                                       value="${productInfo.getProductPrice()}">${productInfo.getProductPrice()?string.currency}</label>
                            </p>
                            <p class="card-text"><strong>Stock: </strong>${productInfo.getProductStock()}</p>



                            <label class="card-text" for="productQuantity">
                                <strong>Quantity: </strong>
                            </label>
                            <input type="number"
                                   id="productQuantity"
                                   name="productQuantity"
                                   value="1"
                                   min="1"
                                   max="${productInfo.getProductStock()}"
                                   oninput="chageSubtotal()">


                            <p class="card-text"><strong>Subtotal: </strong>
                            <#--For JavaScript inHTML-->
                                <label id="subtotal">${(productInfo.getProductPrice())?string.currency}</label>
                            </p>
                        </div>
                        <button type="submit"
                                class="btn btn-primary btn-lg <#if productInfo.getProductStatus()==1>disabled</#if>">
                            Add to Cart
                        </button>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script>
    var price_e = document.getElementById("price");
    var subtotal_e = document.getElementById("subtotal");
    var productQuantity_e = document.getElementById("productQuantity");
    var price = parseFloat(document.getElementById("price").innerText.substr(1));
    var unit = price_e.innerHTML.charAt(0);
    function chageSubtotal() {
        var subtotal = (parseFloat(productQuantity_e.value) * price).toFixed(2);
        subtotal_e.innerHTML = unit + subtotal;
    }
</script>
</html>