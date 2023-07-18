
<link rel="stylesheet" type="text/css" href="Styles/cssOffCanvas.css">

<container class="containers">
    <div>
        <button class="btn btn-outline-info boton-canvas" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasWithBothOptions" aria-controls="offcanvasWithBothOptions">
            <img class="imagenMenu" src="Sources/Images/tres-lineas.png">
        </button>
    </div>
    
    <div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions" aria-labelledby="offcanvasScrollingLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel">Menu</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        
        <div class="offcanvas-body">
            
            <!--Menu Inicial-->
            <div class="divs-offcanvas">
                <form class="form-offcanvas" method="post" name="myForm" action="Menu">
                    <input type="hidden" name="tipoLlamado" value="OffCanvas">
                    <input type="hidden" id="idUs" name="userid" value=<%=us%>>
                    <button type="submit" class="btn btn-outline-info btn-offcanvas border border-0">Menu principal</button>
                </form>
            </div>
                
            <!--Casos Asignados al usuario-->
            <div class="divs-offcanvas">
                <form class="form-offcanvas" method="post" name="myForm" action="SolicitudesUsuario">
                    <input type="hidden" name="userid" value=<%=us%>>
                    <input type="hidden" name="listar" value="1">
                    <button type="submit" class="btn btn-outline-info  btn-offcanvas border border-0">Solicitudes que me han realizado</button>
                </form>
            </div>
                
            <!--Casos Asignados por el usuario-->
            <div class="divs-offcanvas">
                <form class="form-offcanvas" method="post" name="myForm" action="SolicitudesUsuario">
                    <input type="hidden" name="userid" value=<%=us%>>
                    <input type="hidden" name="listar" value="4">
                    <button type="submit" class="btn btn-outline-info  btn-offcanvas border border-0">Solicitudes que he realizado</button>
                </form>
            </div>
        </div>
        
        <div class="position-absolute bottom-0 start-50 translate-middle-x footer-offCanvas">
            CFS SISTEMAS S.A.
        </div>
    </div>
</container>