<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/books/listAll" var="recordsUrl"/>
<c:url value="/books/save" var="addUrl"/>
<c:url value="/books/update" var="editUrl"/>
<c:url value="/books/delete" var="deleteUrl"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="taskManagerApp">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Library Management Screen</title>

    <link rel='stylesheet' type='text/css' media='screen' href='<c:url value="/resources/css/style.css"/>'/>
    <link rel='stylesheet' type='text/css' media='screen' href='<c:url value="/resources/css/css/font-awesome.css"/>'/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>

    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script data-require="angular.js@*" data-semver="1.2.13" src="http://code.angularjs.org/1.2.13/angular.js"></script>
    <script data-require="angular-animate@*" data-semver="1.2.13" src="http://code.angularjs.org/1.2.13/angular-animate.js"></script>
    <script type='text/javascript' src='<c:url value="/resources/js/app.js"/>'></script>

    <script type='text/javascript'>
            urlHolder.records = '${recordsUrl}';
            urlHolder.add = '${addUrl}';
            urlHolder.edit = '${editUrl}';
            urlHolder.del = '${deleteUrl}';


    </script>
</head>
<body>



<div ng-controller="taskManagerController">
    <%--<div style="text-align: right;" id="success-book-panel" class="fadein fadeout success panel" ng-hide="success">--%>
        <%--<span>success: </span>Write your success message here.--%>
    <%--</div>--%>
    <div class="fadein fadeout alert-box add-success panel" ng-hide="addSuccess"><span>success: </span>Book is added</div>
    <div class="fadein fadeout alert-box update-success panel" ng-hide="updateSuccess"><span>success: </span>Book is updated</div>
    <div class="fadein fadeout alert-box delete-success panel" ng-hide="deleteSuccess"><span>NOTICE: </span>Book is deleted successfully</div>
        <div class="fadein fadeout alert-box warning panel" ng-hide="captchaFail"><span>WARNING: </span>Wrong Captcha</div>
    <h2 class="page-title">Library Management Screen</h2>
    <div id="task-panel" class="fadein fadeout showpanel panel" ng-show="addToggle && editToggle && infoGif">
        <div class="panel-heading">
            <i class="panel-title-icon fa fa-tasks"></i>
            <span class="panel-title"><button ng-click="addToggle = !addToggle" class="btn-panel">+ Add Book</button></span>
            <div class="panel-heading-controls">
                <button class="btn-panel" ng-click="checkRadioisSelectedForEditBook()" id='editBtn'>Edit Selected Book</button>
                <button class="btn-panel" ng-click="deleteBook()" id='deleteBtn'>Delete selected book</button>
            </div>
        </div>

        <div class="panel-body">
            <h1 class="book-image">
                Search:  <input ng-model="searchText">
            </h1>  <hr>
            <table class="list-table">

                <thead>
                <tr>
                    <th style="background-color: beige;text-align: left;"></th>
                    <th style="border-top-left-radius: 1em;">Book Name</th>
                    <th style="border-top-right-radius: 1em;">Author</th>

                </tr>
                </thead>
                <tbody>
                <tr  ng-repeat="book in books | filter:searchText">
                    <td style=" border-top-left-radius: 2em;border-bottom-left-radius: 2em;text-align: left;"><input type="radio" name="radioValue" ng-model="$parent.id" id="{{book.id}}" value="{{book.id}}"></td>
                    <td style="text-align: left;width: 220px;"><label id={{$parent.id}}>{{book.name}}</label></td>
                    <td style="text-align: left;width: 220px;"><label>{{book.author}}</label></td>

                </tr>
                </tbody>

            </table>

        </div>
    </div>


    <div id="add-book-panel" class="fadein fadeout addpanel panel" ng-hide="addToggle">
        <div class="panel-heading">
            <span class="panel-title">Add Book</span>
            <div class="panel-heading-controls">
                <button ng-click="cancelAddBook(book)" class="btn-panel">Cancel</button>
            </div>
        </div>
        <div class="panel-body">
            <form>
            <div class="task" >
                <table class="add-task">
                    <tr>
                        <td>Book Name:</td>
                        <td><input class="form-control"  type="text" ng-model="book.name" required /></td>
                    </tr>
                    <tr>
                        <td>Author:</td>
                        <td><input class="form-control"  type="text" ng-model="book.author" required /></td>
                    </tr>

                </table>

                <script type="text/javascript"
                        src="http://www.google.com/recaptcha/api/challenge?k=6LcSSP0SAAAAAJhzW2Ull1TRquskss0-W5N6H8cK">
                </script>

                <br/>
                <div style="text-align: right;" >
                <button ng-click="addBook(book)" class="btn-panel-big">Add New Book</button>
                </div>
                </div>
            </form>
        </div>
    </div>



    <div id="edit-book-panel" class="fadein fadeout addpanel panel" ng-hide="editToggle">
        <div class="panel-heading">
            <span class="panel-title">Edit Book</span>
            <div class="panel-heading-controls">
                <button ng-click="editToggle = !editToggle" class="btn-panel">Cancel</button>
            </div>
        </div>
        <div class="panel-body">
            <form>
                <div class="task" >
                    <table class="add-task">
                        <tr>
                            <td>Book Name:</td>
                            <td><input class="form-control"  type="text" ng-model="name" required /></td>
                        </tr>
                        <tr>
                            <td>Author:</td>
                            <td><input class="form-control"  type="text" ng-model="author" required /></td>
                        </tr>
                        <tr>
                            <td><br/><button ng-click="editBook()" class="btn-panel-big">Edit Book</button></td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>

    <div style="text-align: center;" id="gif-book-panel" class="fadein fadeout addpanel panel" ng-hide="infoGif">
        <img src="http://gifgifs.com/animations/computers-technology/gears/Blue_gears.gif"/>
    </div>


</div>
</body>
</html>