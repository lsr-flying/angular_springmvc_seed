/**
 * Created by LINSHIRUI447 on 2016-09-02.
 */
appModule.config(['$routeProvider',function($routeProvider){
    $routeProvider.
        when('/authorizationMgt', {
            templateUrl: 'views/authorizationMgtView.html',
            controller:'authorizationMgtCtrl'
        }).
        when('/userCenter', {
            templateUrl: 'views/userCenterView.html',
            controller:'userCenterCtrl'
        }).
        otherwise({
            redirectTo: '/userCenter'
        });
}]);

appModule.run(["$location","$rootScope",function( $location,$rootScope) {
    $rootScope.$on('$routeChangeStart',function(event, next, current) {
        console.log("$routeChangeStart");
    });
    $rootScope.$on('$routeChangeSuccess', function(event, current, previous) {
        console.log("$routeChangeSuccess");
    });

    $rootScope.$on('$routeChangeError',function() {
        console.log("$routeChangeError");
    });
    $rootScope.$on('$routeUpdate',function() {
        console.log("$routeUpdate");
    });
}]);