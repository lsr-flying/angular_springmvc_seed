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