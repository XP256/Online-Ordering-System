<#--<#import "/spring.ftl" as spring />-->
<#--<html>-->
<#--<#include "../partials/_header.ftl">-->
<#--<body>-->
<#--<div class="container ">-->
    <#--<#include "../partials/_nav.ftl">-->
    <#--<h1 align="center" class="display-4 mb-5">Edit Profiles</h1>-->
    <#--<div style="width:40%; margin: 25px auto" >-->
        <#--<form action="/profiles"  method="post">-->
            <#--<@spring.bind "user"/>-->
            <#--<div class="form-group">-->
                <#--<label>Email address</label>-->
                 <#--<@spring.bind "user.email"/>-->
                <#--<input readonly value="${currentUser.email!}" type="email" class="form-control form-control-lg" id="email" name="email" placeholder="Enter email" required="true" >-->
                <#--<span class="text-danger"><@spring.showErrors ""/></span>-->
            <#--</div>-->
            <#--<div class="form-group">-->
                <#--<label>Name</label>-->
                 <#--<@spring.bind "user.name"/>-->
                <#--<input value="${currentUser.name!}"type="text" class="form-control form-control-lg" id="name" name="name" placeholder="Your name" required="true" autofocus="true">-->
                <#--<span class="text-danger"><@spring.showErrors ""/></span>-->
            <#--</div>-->
            <#--<div class="form-group">-->
                <#--<label>Password</label>-->
                <#--<@spring.bind "user.password"/>-->
                <#--<input type="password" class="form-control form-control-lg" id="password" name="password" placeholder="Password" required="true">-->
                <#--<span class="text-danger"><@spring.showErrors ""/></span>-->
            <#--</div>-->
            <#--<div class="form-group">-->
                <#--<label>Phone</label>-->
                <#--<@spring.bind "user.phone"/>-->
                <#--<input value="${currentUser.phone!}" type="text" class="form-control form-control-lg" id="phone" name="phone" placeholder="Phone" required="true">-->
                <#--<span class="text-danger"><@spring.showErrors ""/></span>-->
            <#--</div>-->
            <#--<div class="form-group">-->
                <#--<label>Address</label>-->
                <#--<@spring.bind "user.address"/>-->
                <#--<input value="${currentUser.address!}" type="text" class="form-control form-control-lg" id="address" name="address" placeholder="Address" required="true">-->
                <#--<span class="text-danger"><@spring.showErrors ""/></span>-->
            <#--</div>-->
            <#--<@spring.bind "user.role"/>-->
            <#--<input hidden type="text" name="role" value="ROLE_CUSTOMER">-->
            <#--<@spring.bind "user.role"/>-->
            <#--<input hidden type="checkbox" name="active" value="1" checked>-->
            <#--<div class="form-group">-->
                <#--<input type="submit" class="btn btn-lg btn-primary btn-block" value="Submit"/>-->
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
    <h1 align="center" class="display-4 mb-5">Profiles</h1>
<#--Main Area-->
    <div style="width:40%; margin: 25px auto" >
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form action="/sell/profiles"  method="post">
                            <div class="form-group">
                                <label>Email address</label>
                                <input readonly value="${currentUser.email!}" type="email" class="form-control form-control-lg" id="email" name="email" placeholder="Enter email" required="true" >
                            </div>
                            <div class="form-group">
                                <label>Name</label>
                                <input value="${currentUser.name!}"type="text" class="form-control form-control-lg" id="name" name="name" placeholder="Your name" required="true" autofocus="true">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" class="form-control form-control-lg" id="password" name="password" placeholder="Password" required="true">
                            </div>
                            <div class="form-group">
                                <label>Phone</label>
                                <input value="${currentUser.phone!}" type="text" class="form-control form-control-lg" id="phone" name="phone" placeholder="Phone" required="true">
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <input value="${currentUser.address!}" type="text" class="form-control form-control-lg" id="address" name="address" placeholder="Address" required="true">
                            </div>
                            <input hidden type="text" name="role" value="ROLE_CUSTOMER">
                            <input hidden type="checkbox" name="active" value="1" checked>
                            <div class="form-group">
                                <input type="submit" class="btn btn-lg btn-primary btn-block" value="Submit"/>
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



