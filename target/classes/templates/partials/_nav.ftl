<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/sell/buyer/product/list">
        <img src="https://uwaterloo.ca/brand/sites/ca.brand/files/resize/uploads/images/universityofwaterloo_logo_vert_rev_rgb_1-300x195.png" width="30" height="30" class="d-inline-block align-top" alt="">
        Online Ordering System
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">

        <div class="navbar-nav">
            <#if !currentUser?? || currentUser.role=="ROLE_CUSTOMER">

                <a class="nav-item nav-link <#if category?? && category.categoryType == 1>active</#if>"
                   href="/sell/buyer/product/category/1">
                    MEAT
                </a>
                <a class="nav-item nav-link <#if category?? && category.categoryType == 2>active</#if>"
                   href="/sell/buyer/product/category/2">
                    VEGETABLES
                </a>
                <a class="nav-item nav-link <#if category?? && category.categoryType == 3>active</#if>"
                   href="/sell/buyer/product/category/3">
                    SNACK
                </a>
            </#if>
        </div>



        <div class="navbar-nav ml-auto">
            <#if !currentUser?? || currentUser.role=="ROLE_CUSTOMER"  >
                <a class="nav-item nav-link " href="/sell/buyer/cart/list">
                    <i class="fa fa-shopping-cart"></i>
                    Cart
                </a>
            </#if>
            <#if currentUser?? >
                <a class="nav-item nav-link " href="/sell/buyer/order/list">
                    <i class="fa fa-list-ul"></i>
                    Orders
                </a>
                <a class="nav-item nav-link " href="/sell/profiles">
                    ${currentUser.name}
                </a>
                <a class="nav-item nav-link " href="/sell/logout">
                    Sign Out
                </a>
            <#else>
                <a class="nav-item nav-link " href="/sell/login">
                    Sign In
                </a>
                <a class="nav-item nav-link " href="/sell/register">
                    Sign Up
                </a>
            </#if>
        </div>




    </div>
</nav>

<#--set all integer without comma-->
<#setting number_format="computer">