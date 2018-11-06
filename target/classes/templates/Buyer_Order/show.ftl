<html>
<#include "../partials/_header.ftl">
<body>
<div class="container ">
    <#include "../partials/_nav.ftl">
    <h1 align="center" class="display-4 mb-5">Order Detail</h1>

<#--Cart Detail Table-->
    <table class="table table-striped text-center">
        <thead>
        <tr>
            <th scope="col">Photo</th>
            <th scope="col">Name</th>
            <#--<th scope="col">Description</th>-->
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Subtotal</th>

        </tr>
        </thead>
        <tbody>
        <#list items as item>
        <tr>
            <th class="align-middle" scope="row">
                <img height="100px" src="${item.productIcon}">
            </th>
            <td class="align-middle">${item.productName}</td>
            <#--<td class="align-middle">${item.getProductDescription()}</td>-->
            <td class="align-middle">${item.productPrice?string.currency}</td>
            <td class="align-middle">${item.productQuantity}</td>
            <td class="align-middle">${(item.productPrice * item.productQuantity)?string.currency}</td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>

<div align="center" class="col-md-12 column">

                <a href="/sell/buyer/order/list" type="button" class="btn btn-default btn-primary">Back</a>

</div>

</body>
</html>