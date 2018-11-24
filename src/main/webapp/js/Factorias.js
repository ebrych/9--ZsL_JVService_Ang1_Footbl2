var OperatMaquinasModulo = angular.module('OperatMaquinas', ['ui.bootstrap', 'commonModule', 'ngTable', 'ngStorage', 'ngSanitize', 'ngAnimate']);

//controlador
OperatMaquinasModulo.controller('OperatMaquinasController', ['$scope', 'CommonFactory', 'OperatMaquinasFactory','SistemaFactory', '$http', '$sce', '$log', 'NgTableParams', '$uibModal',
    function ($scope, CommonFactory, OperatMaquinasFactory, SistemaFactory, MiService, $http, $sce, $log, NgTableParams, $uibModal) {

        //variables
        var idtfUss = null;
        $scope.OtMaquina = null;
        $scope.listPais = null;
        $scope.listLocales = null;
        $scope.listOpeMaquinas = null;
        $scope.alumnos = null;
        $scope.fechaActual = null;
       

        //init
        $scope.init = function (idUss) {
            idtfUss = idUss;
            //lista paises
            OperatMaquinasFactory.getListPaises(idtfUss).then(function (response) {
                $scope.listPais = response.data;
            })
        }

        //listarLocales
        $scope.ListaDeLocales = function () {
            OperatMaquinasFactory.getListLocales(idtfUss,$scope.paisSelectList).then(function (response) {
                $scope.listLocales = response.data;
            })
        };
        //consultarLista
        $scope.consultaLista = function () {
            OperatMaquinasFactory.getListaOperatMaquinas($scope.paisSelectList, $scope.localSelectList, idtfUss).then(function (response) {
                $scope.listOpeMaquinas = response.data          
            });
        }
        //seleccionRow
        $scope.selectRow = function (OtMaq) {
            $('#dataTable_conten').on('click', '.clickable-row', function (event) {
                $(this).addClass('active').siblings().removeClass('active');
            });
            $scope.OtMaquina = OtMaq;
        };





        //test
        $scope.saludo = function () {
            return OperatMaquinasFactory.getSaludo();
        };
        $scope.direccion = function () {
            return OperatMaquinasFactory.getBaseUrl();
        };
        
        



        //actions botones
        $scope.tecComent = function () {
            if ($scope.OtMaquina != null) {
                $scope.fechaActual = OperatMaquinasFactory.getFechaActual();
                $("#nuevaNotaModal").modal('show');
            }
        }
        $scope.listTecComent = function () {
            if ($scope.OtMaquina != null) {
                $("#listaNotaModal").modal('show');
            }
        }
        $scope.prodComent = function () {
            if ($scope.OtMaquina != null) {
                $("#nuevaProdModal").modal('show');
            }
        }
        $scope.listProdComent = function () {
            if ($scope.OtMaquina != null) {
                $("#listaProdModal").modal('show');
            }   
        }
        $scope.finalizar = function () {
            alert("finaliza maquina:" + $scope.OtMaquina);
        }

    }]);




//Servicios del módulo
OperatMaquinasModulo.factory('OperatMaquinasFactory', ["$http", "BaseUrl", function ($http, BaseUrl) {
    var dataFactory = {};
    dataFactory.getSaludo = function () {
        return "hola mundo mundial";
    },

        dataFactory.getListaOperatMaquinas = function (pais, local, usuario) {
            return $http({
                url: BaseUrl + "/OperatividadMaquinas/listaOperatMaquinas",
                method: "POST",
                params: { pais: pais, local: local, usuario: usuario }
            });
        },
        dataFactory.getListPaises = function (idtUs) {
            return $http({
                url: BaseUrl + "/OperatividadMaquinas/listaPaises",
                method: "POST",
                params: { id: idtUs }
            });
        },
        dataFactory.getListLocales = function (idtUs, idtPais) {
            return $http({
                url: BaseUrl + "/OperatividadMaquinas/listaLocal",
                method: "POST",
                params: { usuario: idtUs, pais: idtPais }
            });
        },
        dataFactory.getEventosOT = function (idOt) {
            return $http({
                url: BaseUrl + "/OperatividadMaquinas/listaEventosOt",
                method: "POST",
                params: { usuario: idtUs, pais: idtPais }
            });
        },
        dataFactory.insertEvento = function (idOt, observacion, usuario) {
            return $http({
                url: BaseUrl + "/OperatividadMaquinas/listaLocal",
                method: "POST",
                params: { idOt: idOt, observacion: observacion, usuario: usuario }
            });
        },
        dataFactory.validaRol = function (usuario,sistema) {
            return $http({
                url: BaseUrl + "/OperatividadMaquinas/validaRol",
                method: "POST",
                params: { usuario: usuario, sistema:sistema }
            });
        },
        dataFactory.cierraOpMaq = function () {
            return $http({
                url: BaseUrl + "/OperatividadMaquinas/cierreOpMaquina",
                method: "POST",
                params: { usuario: usuario, sistema: sistema }
            });
        },
        dataFactory.getFechaActual = function () {
            var dt = new Date();
            var month = dt.getMonth() + 1;
            var day = dt.getDate();
            var year = dt.getFullYear();
            return year + '-' + month + '-' + day;
        },
        dataFactory.getInfoAlumnos = function () {
            var data = [{ nombre: 'Paco', pais: 'España' },
            { nombre: 'Manuel', pais: 'Chile' },
            { nombre: 'Laura', pais: 'Argentina' }];
            return data;
        }
        
    return dataFactory;
}]);
