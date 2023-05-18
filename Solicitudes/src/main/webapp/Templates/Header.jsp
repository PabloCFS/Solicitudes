<%-- 
    Document   : Header
    Created on : 18 may. 2023, 09:45:49
    Author     : pablo.elizondo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="Styles/cssHeader.css">

<section class="Section-Header">
    <container class="Container-header">
        <div class="columns w-row columns-header">
            
            <div class="column w-col w-col-6 column1-header">
                <img src="Sources/Images/CFS_logo_Blanco.png" class="img-fluid border border-secondary-subtle rounded logoCFS" ></img>
            </div>
            
            <div class="colum-2 w-col w-col-6 column2-header">
                <form action="ServletLogout" method="post" name="form">
                    <button type="submit" name="salir" value="Salir" class="button-submit-exit btn btn-outline-danger">
                        Salir
                    </button>
                </form>
            </div>
            
        </div>
    </container>
</section>