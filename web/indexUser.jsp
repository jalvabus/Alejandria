<%-- 
    Document   : indexUser
    Created on : 8/03/2019, 10:53:33 AM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Index Usuario</title>

    <!-- CSS Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- CSS Stock de libros-->
    <link rel="stylesheet" href="css/StLiStock.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Lora|Merriweather:300,400" rel="stylesheet">

    <!-- AngularJS -->
    <script src="js/angular/angular.js"></script>
    <!-- SweetAlert -->
    <script src="js/sweetalert/sweetalert.js"></script>
    <!-- Iconos -->
    <link rel="stylesheet" href="font-awesome/css/font-awesome.css">

    <!-- Favicons -->
    <link href="img/favicon.png" rel="icon">
    <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

</head>

<body>
    <script type="text/javascript" src="scriptUsuario2.js"></script>

    <!-- PRE LOADER -->

    <div class="preloader">
        <div class="sk-spinner sk-spinner-wordpress">
            <span class="sk-inner-circle"></span>
        </div>
    </div>

    <!-- Navigation section  -->

    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">

            <div class="navbar-header">
                <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon icon-bar"></span>
                    <span class="icon icon-bar"></span>
                    <span class="icon icon-bar"></span>
                </button>
                <a href="indexAlejandria.jsp" class="navbar-brand">Alejandr�a</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="#" onclick="DinamicoDiv('inicio');">Perfil</a></li>
                    <li><a href="#" onclick="DinamicoDiv('eventos');">Eventos</a></li>
                    <li><a href="#" onclick="DinamicoDiv('libros');">Libros</a></li>
                    <li><a href="#" onclick="DinamicoDiv('compras');">Compras</a></li>
                    <li><a href="#" onclick="DinamicoDiv('premios');">Premios</a></li>
                    <li><a href="#" onclick="DinamicoDiv('sugerencias');">Sugerencias</a></li>
                    <li><a href="indexGeneral.jsp">Salir</a></li>
                </ul>
            </div>

        </div>
    </div>

    <!-- Home Section 
    
            <section id="home" class="main-home parallax-section">
                <div class="overlay"></div>
                <div id="particles-js"></div>
                <div class="container">
                    <div class="row">
    
                        <div class="col-md-12 col-sm-12">
                            <h1>Alejandr�a</h1>
                            <h4>Librer�a Alejandria</h4>
                        </div>
    
                    </div>
                </div>
            </section>
            -->

    <!-- Seccion de Inicio -->
    <div id="inicio">
        <section id="blog">
            <div class="container">
                <div class="row">

                    <div class="col-md-offset-1 col-md-10 col-sm-12">
                        <div class="blog-post-thumb">

                            <div class="blog-post-title">
                                <h3><a href="single-post.html">Bienvenido </a></h3>
                            </div>
                            <div class="blog-post-format">

                            </div>

                            <div class="blog-post-thumb">
                                <div class="blog-post-title">
                                    <h3><a href="single-post.html">Activar tarjeta de prepago</a></h3>
                                </div>
                                <div class="blog-post-des">
                                    <div class="blog-comment-form">
                                        <p>
                                            <input type="text" class="form-control" placeholder="Ingrese tarjeta" id="tar"
                                                required>
                                        </p>
                                        <div class="col-md-3 col-sm-4">
                                            <input name="submit" type="button" class="form-control" id="actTar" value="Abonar"
                                                onclick="enviarTarjeta1();"><br>
                                        </div>
                                    </div>
                                    <!-- designar un div que traer� la respuesta de la insercion del nuevo evento-->

                                    <div class="blog-post-image">
                                        <img src="images/blog-image3.jpg" class="img-responsive" alt="Blog Image 3">
                                    </div>
                                    <h3>What is Personal Blog Theme?</h3>
                                    <p>Nulla tincidunt eu velit elementum accumsan. Vivamus euismod tempor interdum.
                                        Vivamus non scelerisque ex, et interdum leo. In bibendum lacus vitae felis
                                        egestas,
                                        at consectetur metus facilisis. Morbi tellus dolor, porta dignissim enim sit
                                        amet,
                                        dapibus sagittis erat. In blandit elit sit amet dui aliquet congue nec vel
                                        quam.
                                        Integer id tristique libero.</p>
                                </div>


                            </div>

                            <div class="blog-post-thumb">
                                <div class="blog-post-video">
                                    <div class="embed-responsive embed-responsive-16by9">
                                        <iframe class="embed-responsive-item" src="" allowfullscreen></iframe><!-- Aqui la va url del video de youtube -->
                                    </div>
                                </div>
                                <div class="blog-post-title">
                                    <h3><a href="single-post.html">Nam interdum maximus dolor faucibus</a></h3>
                                </div>
                                <div class="blog-post-format">
                                    <span><a href="#"><img src="images/author-image1.jpg" class="img-responsive img-circle">
                                            Jen Lopez</a></span>
                                    <span><i class="fa fa-date"></i> May 30, 2017</span>
                                    <span><a href="#"><i class="fa fa-comment-o"></i> 63 Comments</a></span>
                                </div>
                                <div class="blog-post-des">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                                        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                                        nostrud
                                        exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
                                        irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
                                        nulla
                                        pariatur.</p>
                                    <a href="single-post.html" class="btn btn-default">Continue Reading</a>
                                </div>
                            </div>

                            <div class="blog-post-thumb">
                                <div class="blog-post-image">
                                    <a href="single-post.html">
                                        <img src="images/blog-image3.jpg" class="img-responsive" alt="Blog Image">
                                    </a>
                                </div>
                                <div class="blog-post-title">
                                    <h3><a href="single-post.html">The ingredients that make a great burger</a></h3>
                                </div>
                                <div class="blog-post-format">
                                    <span><a href="#"><img src="images/author-image2.jpg" class="img-responsive img-circle">
                                            Leo Dennis</a></span>
                                    <span><i class="fa fa-date"></i> April 18, 2017</span>
                                    <span><a href="#"><i class="fa fa-comment-o"></i> 124 Comments</a></span>
                                </div>
                                <div class="blog-post-des">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                                        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                                        nostrud
                                        exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
                                        irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
                                        nulla
                                        pariatur.</p>
                                    <a href="single-post.html" class="btn btn-default">Continue Reading</a>
                                </div>
                            </div>

                            <div class="blog-post-thumb">
                                <div class="blog-post-image">
                                    <a href="single-post.html">
                                        <img src="images/blog-image4.jpg" class="img-responsive" alt="Blog Image">
                                    </a>
                                </div>
                                <div class="blog-post-title">
                                    <h3><a href="single-post.html">Vestibulum vel mauris nec ex tempus</a></h3>
                                </div>
                                <div class="blog-post-format">
                                    <span><a href="#"><img src="images/author-image1.jpg" class="img-responsive img-circle">
                                            Jen Lopez</a></span>
                                    <span><i class="fa fa-date"></i> March 12, 2017</span>
                                    <span><a href="#"><i class="fa fa-comment-o"></i> 256 Comments</a></span>
                                </div>
                                <div class="blog-post-des">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                                        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                                        nostrud
                                        exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
                                        irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
                                        nulla
                                        pariatur.</p>
                                    <a href="single-post.html" class="btn btn-default">Continue Reading</a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
        </section>
    </div>
    <!-- Seccion de Libros -->

    <div id="eventos" style="display: none">
        <section>
            <div class="container">
                <div class="row">

                    <div class="col-md-offset-1 col-md-10 col-sm-12">
                        <h2>Aqui se realizan las compras de los eventos del cliente</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                            labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                            voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>

                        <form action="#" method="post">
                            <div class="col-md-4 col-sm-4">
                                <input name="name" type="text" class="form-control" id="name" placeholder="Name">
                            </div>
                            <div class="col-md-4 col-sm-4">
                                <input name="email" type="email" class="form-control" id="email" placeholder="Email">
                            </div>
                            <div class="col-md-4 col-sm-4">
                                <input name="subject" type="text" class="form-control" id="subject" placeholder="Subject">
                            </div>
                            <div class="col-md-12 col-sm-12">
                                <textarea name="message" rows="5" class="form-control" id="message" placeholder="Message"></textarea>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <input name="submit" type="submit" class="form-control" id="submit" value="Send">
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </section>
    </div>

    <!-- Seccion Modal de Libros -->
       
    <div class="container">
                <div ng-app="myApp" ng-controller="myCtrl" class="modal" id="ModalLibro" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Registrar Libro</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <div class="col-md-12">
                            <form>
                                <div class="form-group col-md-6 col-sm-12 col-lg-6 col-xl-6"  >
                                    <label for="fGL-ISBN">Código ISBN</label>
                                    <input type="text" class="form-control" ng-model="ISBN" id="ISBN" >
                                </div>
                                <div class="form-group col-md-6 col-sm-12 col-lg-6 col-xl-6">
                                    <label for="fGL-TiLibro">Titulo del Libro</label>
                                    <input type="text" class="form-control" id="TiLibro" placeholder="Titulo del libro"  required>
                                </div>
                                <div class="form-group col-md-6 col-sm-12 col-lg-6 col-xl-6">
                                    <label for="Select">Autor del libro</label>
                                    <input type="text" class="form-control" id="AutorL" placeholder="Autor del libro"  required>
                                </div>
                                <div class="form-group col-md-6 col-sm-12 col-lg-6 col-xl-6">
                                    <label for="SelectCategoria">Categoria</label>
                                    <select id="SelectCategoria" class="form-control" required>
                                        <option value="1">Aventura</option>
                                        <option value="2">Ciencia Ficción</option>
                                        <option value="3">Deportes</option>
                                        <option value="4">Infantil</option>
                                        <option value="5">Novela</option>
                                        <option value="6">Policial</option>
                                        <option value="7">Política</option>
                                        <option value="8">Romántica</option>
                                        <option value="9">Terror</option>
                                        <option value="10">Viajes</option>                                        
                                    </select>
                                </div>
                                <div class="form-group col-md-6 col-sm-12 col-lg-6 col-xl-6">
                                    <label for="fGL-Editorial">Editorial</label>
                                    <input type="text" class="form-control" ng-model="Editorial" id="Editorial" placeholder="Editorial" required>
                                </div>
                                <div class="form-group col-md-6 col-sm-12 col-lg-6 col-xl-6">
                                    <label for="fGL-Costo">Costo</label>
                                    <input type="text" class="form-control" ng-model="CostoLi" id="CostoLi" placeholder="$$$$" required>
                                </div>
                                <div class="form-group col-md-6 col-sm-12 col-lg-6 col-xl-6">
                                    <label for="fGL-LibroDesc">Descripción</label>
                                    <TEXTAREA class="form-control" ng-model="LibroDesc" id="LibroDesc" placeholder="Escribe algo..." required></TEXTAREA >
                                </div>
                                <div class="form-group col-md-6 col-sm-12 col-lg-6 col-xl-6">
                                    <label class="mt-3" for="customRangeStock">Stock</label>
                                    <input class="custom-range" ng-model="customRangeStock" id="customRangeStock" type="range" name="foo" min="10" max="100" style="width: 240px;">
                                    <output for="foo" onforminput="value = foo.valueAsNumber;" style="margin-bottom: -45px;"></output>
                                </div>

                                <div class="form-group col-md-12 col-sm-12 col-lg-12 col-xl-12">
                                <input type="file" class="form-control-file" ng-model="FormControlFileLibro" id="FormControlFileLibro" required>
                                </div>
                            </form>
                        </div>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-primary" onclick="IngresarLibro();">Guardar Cambios</button>
                      </div>
                    </div>
                  </div>
                </div>
            </div>
             <!-- Seccion de Libros -->
            <div id="libros" >
                    <section id="gallery">
                        <div class="container">
                                        <!-- Button trigger modal -->
                                <div class="col-md-5 col-md-offset-1 col-sm-6">
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#ModalLibro" >
                                          Ingresar nuevo Libro
                                    </button>
                                </div>
                                <div class="col-md-offset-1 col-md-10 col-sm-12">
                                    <h2>Aqui se visualizan y compran los libros</h2>
                                    <p>Aliquam blandit velit nisi, sed fringilla felis lacinia sed. Integer vitae porta felis.
                                        Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.
                                        Phasellus non tristique lacus. Suspendisse ut tortor vitae risus lacinia tristique. Aliquam
                                        sed consectetur libero.</p>
                                    <p>Morbi tellus dolor, porta dignissim enim sit amet, dapibus sagittis erat. In blandit elit
                                        sit amet dui aliquet congue nec vel quam. Integer id tristique libero.</p>
                                    <span></span>
                                    <div class="col-md-6 col-sm-6">
                                        <div class="gallery-thumb">
                                            <a href="images/gallery-image1.jpg" class="image-popup">
                                                <img src="images/gallery-image1.jpg" class="img-responsive" alt="Gallery Image">
                                            </a>
                                        </div>
                                    </div>

                                    <div class="col-md-6 col-sm-6">
                                        <div class="gallery-thumb">
                                            <a href="images/gallery-image2.jpg" class="image-popup">
                                                <img src="images/gallery-image2.jpg" class="img-responsive" alt="Gallery Image">
                                            </a>
                                        </div>
                                    </div>

                                    <div class="col-md-6 col-sm-6">
                                        <div class="gallery-thumb">
                                            <a href="images/gallery-image3.jpg" class="image-popup">
                                                <img src="images/gallery-image3.jpg" class="img-responsive" alt="Gallery Image">
                                            </a>
                                        </div>
                                    </div>

                                    <div class="col-md-6 col-sm-6">
                                        <div class="gallery-thumb">
                                            <a href="images/gallery-image4.jpg" class="image-popup">
                                                <img src="images/gallery-image4.jpg" class="img-responsive" alt="Gallery Image">
                                            </a>
                                        </div>
                                    </div>

                                    <div class="col-md-6 col-sm-6">
                                        <div class="gallery-thumb">
                                            <a href="images/gallery-image5.jpg" class="image-popup">
                                                <img src="images/gallery-image5.jpg" class="img-responsive" alt="Gallery Image">
                                            </a>
                                        </div>
                                    </div>

                                    <div class="col-md-6 col-sm-6">
                                        <div class="gallery-thumb">
                                            <a href="images/gallery-image6.jpg" class="image-popup">
                                                <img src="images/gallery-image6.jpg" class="img-responsive" alt="Gallery Image">
                                            </a>
                                        </div>
                                    </div>

                                    <div class="col-md-12 col-sm-12">
                                        <p>Aliquam blandit velit nisi, sed fringilla felis lacinia sed. Integer vitae porta felis.
                                            Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos
                                            himenaeos. Phasellus non tristique lacus.</p>
                                    </div>
                                </div>

                            </div>
                    </section>
                </div>
            </div>
    <!-- Footer Section -->


    <footer>
        <div class="container">
            <div class="row">

                <div class="col-md-5 col-md-offset-1 col-sm-6">
                    <h3>Quienes somos</h3>
                    <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt
                        ut labore et dolore magna aliquyam erat, sed diam voluptua.</p>
                    <div class="footer-copyright">
                        <p>Copyright &copy; 2017 Your Company - Design: Tooplate</p>
                    </div>
                </div>

                <div class="col-md-5 col-md-offset-1 col-sm-6">
                    <h3>Misi�n</h3>
                    <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt
                        ut labore et dolore magna aliquyam erat, sed diam voluptua.</p>
                </div>

                <div class="col-md-5 col-md-offset-1 col-sm-6">
                    <h3>Vision</h3>
                    <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt
                        ut labore et dolore magna aliquyam erat, sed diam voluptua.</p>
                </div>

                <div class="col-md-4 col-md-offset-1 col-sm-6">
                    <h3>Contacto</h3>
                    <p><i class="fa fa-globe"></i> 512 Delicious Street, San Francisco, CA 10880</p>
                    <p><i class="fa fa-phone"></i> (55)23-49-86-45</p>
                    <p><i class="fa fa-save"></i> alejandria@yahoo.com</p>
                </div>

                <div class="clearfix col-md-12 col-sm-12">
                    <hr>
                </div>

                <div class="col-md-12 col-sm-12">
                    <ul class="social-icon">
                        <li><a href="#" class="fa fa-facebook"></a></li>
                        <li><a href="#" class="fa fa-twitter"></a></li>
                        <li><a href="#" class="fa fa-google-plus"></a></li>
                        <li><a href="#" class="fa fa-dribbble"></a></li>
                        <li><a href="#" class="fa fa-linkedin"></a></li>
                    </ul>
                </div>

            </div>
        </div>
    </footer>

    <!-- Back top -->
    <a href="#back-top" class="go-top"><i class="fa fa-angle-up"></i></a>

    <!-- SCRIPTS -->

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/particles.min.js"></script>
    <script src="js/app.js"></script>
    <script src="js/jquery.parallax.js"></script>
    <script src="js/smoothscroll.js"></script>
    <script src="js/custom.js"></script>
    <script src="js/modulo1/JsStock.js"></script>


</body>

</html>