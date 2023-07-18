
<div class="modal fade" id="modalNuevoMensaje" tabindex="-1" data-bs-backdrop="static" data-bs-keyboard="false" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            
            <!-- MODAL HEADER-->
            <div class="modal-header">
                <h5 class="modal-title fs-5" id="staticBackdropLabel">Nuevo Mensaje</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            
            <!-- FORM -->
            <form class="form-agregar-Mensaje" action="AgregarMensaje" method="post" name="myForm" id="formAgregarMensaje">
                <div class="modal-body">
                    <label class="col-form-label"><Strong>Mensaje</Strong></label>
                    <textarea class="form-control" name="mensaje" id="mensaje" placeholder="Escriba su mensaje" rows="4" aria-label="Width textarea" required="true"></textarea>

                    <input type="hidden" id="idSolicitud" name="idSolicitud" value=<%=miSolicitud.getId()%>>
                    <input type="hidden" id="usuarioComenta" name="userid" value=<%=us%>>
                    <input type="hidden" id="tipoList" name="listar" value=<%=list%>>
                </div>
                    
                <div class="modal-footer">
                    <button id="btnNuevoMensaje" type="submit" class="btn btn-outline-success">Aceptar</button>
                    <button id="btnNuevoMensajeX" type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </form>
                    
        </div>
    </div>
</div>
                
                