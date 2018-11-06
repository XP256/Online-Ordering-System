<#--<#import "/spring.ftl" as spring />-->
<#--<html>-->
<#--<#include "../partials/_header.ftl">-->
<#--<body>-->
<#--<div class="container ">-->
    <#--<#include "../partials/_nav.ftl">-->
    <#--<h1 align="center" class="display-4 mb-5">Sign Up</h1>-->
    <#--<div style="width:40%; margin: 25px auto" >-->


        <#--<form role="form" action="/sell/register" method="post">-->




            <#--<div class="form-group">-->
                <#--<label>Email address</label>-->
                <#--<input value="${user.email!}"  type="email" class="form-control form-control-lg" id="email" name="email" placeholder="Enter email" required="true" autofocus="true">-->
            <#--</div>-->

            <#--<div class="form-group">-->
                <#--<label>Name</label>-->
                <#--<input value="${user.name!}" type="text" class="form-control form-control-lg" id="name" name="name" placeholder="Your name" required="true">-->
            <#--</div>-->

            <#--<div class="form-group">-->
                <#--<label>Password</label>-->
                <#--<input type="password" class="form-control form-control-lg" id="password" name="password" placeholder="Password" required="true">-->
            <#--</div>-->

            <#--<div class="form-group">-->
                <#--<label>Phone</label>-->
                <#--<input value="${user.phone!}" type="text" class="form-control form-control-lg" id="phone" name="phone" placeholder="Phone" required="true">-->
            <#--</div>-->

            <#--<div class="form-group">-->
                <#--<label>Address</label>-->
                <#--<input value="${user.address!}" type="text" class="form-control form-control-lg" id="address" name="address" placeholder="Address" required="true">-->
            <#--</div>-->









            <#--<input hidden type="text" name="id" value="${(user.id)!''}">-->
            <#--<input hidden type="text" name="role" value="ROLE_CUSTOMER">-->
            <#--<input hidden type="checkbox" name="active" value="1" checked>-->

            <#--<div class="form-group">-->
                <#--<input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign Up"/>-->
            <#--</div>-->





        <#--</form>-->




    <#--</div>-->



<#--</div>-->

<#--</body>-->
<#--</html>-->






<html>
<#include "../partials/_header.ftl">

<body>
<div id="wrapper" class="toggled">
<#--sidebar-->
            <#include "../partials/_nav.ftl">
<h1 align="center" class="display-4 mb-5">Sign Up</h1>
<#--Main Area-->
    <div style="width:40%; margin: 25px auto" >
        <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" action="/sell/register" method="post">




                        <div class="form-group">
                            <label>Email address</label>
                            <#--<input value="${userInfo.email!}"  type="email" class="form-control form-control-lg" id="email" name="email" placeholder="Enter email" required="true" autofocus="true">-->
                            <input   type="email" class="form-control form-control-lg" id="email" name="email" placeholder="Enter email" required="true" autofocus="true">
                        </div>

                        <div class="form-group">
                            <label>Name</label>
                            <#--<input value="${userInfo.name!}" type="text" class="form-control form-control-lg" id="name" name="name" placeholder="Your name" required="true">-->
                            <input  type="text" class="form-control form-control-lg" id="name" name="name" placeholder="Your name" required="true">
                        </div>

                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" class="form-control form-control-lg" id="password" name="password" placeholder="Password" required="true">
                        </div>

                        <div class="form-group">
                            <label>Phone</label>
                            <input  type="text" class="form-control form-control-lg" id="phone" name="phone" placeholder="Phone" required="true">
                            <#--<input value="${userInfo.phone!}" type="text" class="form-control form-control-lg" id="phone" name="phone" placeholder="Phone" required="true">-->
                        </div>

                        <div class="form-group">
                            <label>Address</label>
                            <input  type="text" class="form-control form-control-lg" id="address" name="address" placeholder="Address" required="true">
                            <#--<input value="${userInfo.address!}" type="text" class="form-control form-control-lg" id="address" name="address" placeholder="Address" required="true">-->
                        </div>









                        <#--<input hidden type="text" name="id" value="${(userInfo.id)!''}">-->
                        <input hidden type="text" name="role" value="ROLE_CUSTOMER">
                        <input hidden type="checkbox" name="active" value="1" checked>

                        <div class="form-group">
                            <input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign Up"/>
                        </div>





                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>
</div>
</body>
</html>



