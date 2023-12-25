<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale=1">
    <title>Login</title>
    <base href="${pageContext.servletContext.contextPath}/">
    <link rel="stylesheet"
          href="<c:url value='/resources/css/custom-css/style.css' />">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<!--------------------------- Main Container ----------------------------->
<div class="container d-flex justify-content-center align-items-center min-vh-100">

    <!--------------------------- Login Container ----------------------------->
    <div class="row border rounder-5 p-3 bg-white shadow box-area">

        <!--------------------------- Left Box ----------------------------->
        <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box" style="background: #103cbe">
            <div class= "featured-image mb-3">
                <img src="<spring:url value='/resources/images/login/thumnail.png' />" class="img-fluid justify-content-center" style="width: 250px;" alt="Thumbnail" />
                <p class="text-white fs-2" style="font-family: 'Courier New', Courier, monospace; font-weight: 600; ">Personal Finance Management</p>
                <small class="text-white text-wrap text-center" style="width: 17rem; font-family:'Courier New', Courier, monospace  ">Web application for support managing personal expenses and income.</small>
            </div>
        </div>
        <!--------------------------- Right Box ----------------------------->
        <div class="col-md-6 right-box">
            <div class="row align-items-center">
                <div class="header-text mb-4 text-center">
                    <h2>Hello Again</h2>
                    <p>We are happy to have you back</p>
                </div>

                <form:form action = "auth/login" method="post" modelAttribute="user">
                    <div class="input-group mb-3">
                        <form:input type = "text" class = "form-control form-control-lg bg-light fs-6" id="username" placeholder="Username"  path="username"/>
                    </div>
                    <label class="mb-1">
                        <h6 class="mb-0 text-sm">
<%--                            <form:errors path="id" cssClass="errors" />--%>
                        </h6>
                    </label>
                    <div class="input-group mb-1">
                        <form:input type = "password" class = "form-control form-control-lg bg-light fs-6"  path="password" id="password" placeholder="Password"/>
                    </div>
                    <label class="mb-1">
                        <h6 class="mb-0 text-sm">
<%--                            <form:errors path="id" cssClass="errors" />--%>
                        </h6>
                    </label>
                    <div class="input-group mb-5 d-flex justify-content-between">
                        <div class = "form-check">
                            <input type = "checkbox" class="form-check-input" id = "formCheck">
                            <label for ="formCheck" class="form-check-label text-secondary"><small>Remember Me</small></label>
                        </div>
                        <div class="forgot">
                            <small><a href="#">Forgot Password</a> </small>
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <button class="btn btn-lg btn-primary w-100 fs-6" type="submit" value = "login">Login</button>
                    </div>
                </form:form>

                <div class="row text-secondary text-md-end">
                    <small>Don't have account?<a href="auth/register">Sign Up</a> </small>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
