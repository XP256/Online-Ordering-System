<html>
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
    <#--sidebar-->
    <#include "../common/nav.ftl">


    <#--Main Area-->
    <div id="page-content-wrapper">
        <div class="container">
        <div class="row clearfix">
            <div class="col-md-4 column">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Total Amount</th>

                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.orderAmount}</td>

                    </tr>

                    </tbody>
                </table>
            </div>

        <#--订单详情表数据-->
            <div class="col-md-12 column">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Product Price</th>
                        <th>Product Quantity</th>
                        <th>Total Amount</th>
                    </tr>
                    </thead>
                    <tbody>
                <#list orderDTO.orderDetailList as orderDetail>
                <tr>
                    <td>${orderDetail.productId}</td>
                    <td>${orderDetail.productName}</td>
                    <td>${orderDetail.productPrice}</td>
                    <td>${orderDetail.productQuantity}</td>
                    <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                </tr>
                </#list>
                    </tbody>
                </table>
            </div>

        <#--操作-->
            <div class="col-md-12 column">
            <#if orderDTO.getOrderStatusEnum().message == "NEW ORDER!">
                <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">Finish Order</a>
                <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">Cancel Order</a>
            </#if>
            </div>
        </div>
    </div>
    </div>
</div>

</body>
</html>








<#--<html>-->
<#--&lt;#&ndash;<#include "../common/header.ftl">&ndash;&gt;-->

<#--<body>-->
<#--<div id="wrapper" class="toggled">-->

<#--&lt;#&ndash;边栏sidebar&ndash;&gt;-->
    <#--&lt;#&ndash;<#include "../common/nav.ftl">&ndash;&gt;-->


<#--&lt;#&ndash;主要内容content&ndash;&gt;-->
    <#--<div id="page-content-wrapper">-->
        <#--<div class="container">-->
            <#--<div class="row clearfix">-->
                <#--<div class="col-md-4 column">-->
                    <#--<table class="table table-bordered">-->
                        <#--<thead>-->
                        <#--<tr>-->
                            <#--<th>订单id</th>-->
                            <#--<th>订单总金额</th>-->
                        <#--</tr>-->
                        <#--</thead>-->
                        <#--<tbody>-->
                        <#--<tr>-->
                            <#--<td>${orderDTO.orderId}</td>-->
                            <#--<td>${orderDTO.orderAmount}</td>-->
                        <#--</tr>-->
                        <#--</tbody>-->
                    <#--</table>-->
                <#--</div>-->

            <#--&lt;#&ndash;订单详情表数据&ndash;&gt;-->
                <#--<div class="col-md-12 column">-->
                    <#--<table class="table table-bordered">-->
                        <#--<thead>-->
                        <#--<tr>-->
                            <#--<th>商品id</th>-->
                            <#--<th>商品名称</th>-->
                            <#--<th>价格</th>-->
                            <#--<th>数量</th>-->
                            <#--<th>总额</th>-->
                        <#--</tr>-->
                        <#--</thead>-->
                        <#--<tbody>-->
                        <#--<#list orderDTO.orderDetailList as orderDetail>-->
                        <#--<tr>-->
                            <#--<td>${orderDetail.productId}</td>-->
                            <#--<td>${orderDetail.productName}</td>-->
                            <#--<td>${orderDetail.productPrice}</td>-->
                            <#--<td>${orderDetail.productQuantity}</td>-->
                            <#--<td>${orderDetail.productQuantity * orderDetail.productPrice}</td>-->
                        <#--</tr>-->
                        <#--</#list>-->
                        <#--</tbody>-->
                    <#--</table>-->
                <#--</div>-->

            <#--&lt;#&ndash;操作&ndash;&gt;-->
                <#--<div class="col-md-12 column">-->
                <#--<#if orderDTO.getOrderStatusEnum().message == "新订单">-->
                    <#--<a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>-->
                    <#--<a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>-->
                <#--</#if>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#--</body>-->
<#--</html>-->