<?php
/**
 * Zend Framework (http://framework.zend.com/)
 *
 * @link      http://github.com/zendframework/ZendSkeletonApplication for the canonical source repository
 * @copyright Copyright (c) 2005-2013 Zend Technologies USA Inc. (http://www.zend.com)
 * @license   http://framework.zend.com/license/new-bsd New BSD License
 */
/*
 * config by xum
 * ***personal style***
 * */
$user = array(
	'type'    => 'Literal',
	'options' => array(
		'route'    => '/user',
        'defaults' => array(
			'__NAMESPACE__' => 'User\Controller',
			'controller'    => 'Index',
			'action'        => 'index',
		),
	),
);

$user_login = array(
	'type'    => 'Literal',
	'options' => array(
		'route'    => '/login',
		'defaults' => array(
			'__NAMESPACE__' => 'User\Controller',
			'controller'    => 'Index',
			'action'        => 'login',
		),
	),
);

$user_register = array(
	'type'    => 'Literal',
	'options' => array(
		'route'    => '/register',
		'defaults' => array(
			'__NAMESPACE__' => 'User\Controller',
			'controller'    => 'Index',
			'action'        => 'register',
		),
	),
);

$user_list = array(
	'type'    => 'Literal',
	'options' => array(
		'route'    => '/list',
		'defaults' => array(
			'__NAMESPACE__' => 'User\Controller',
			'controller'    => 'Index',
			'action'        => 'list',
		),
	),
);

$user_edit = array(
	'type'    => 'Literal',
	'options' => array(
		'route'    => '/edit',
		'defaults' => array(
			'__NAMESPACE__' => 'User\Controller',
			'controller'    => 'Index',
			'action'        => 'edit',
		),
	),
);

$user_delete = array(
	'type'    => 'Literal',
	'options' => array(
		'route'    => '/delete',
		'defaults' => array(
			'__NAMESPACE__' => 'User\Controller',
			'controller'    => 'Index',
			'action'        => 'delete',
		),
	),
);

$user['may_terminate'] = true;
$user['child_routes']['login'] = $user_login;
$user['child_routes']['register'] = $user_register;
$user['child_routes']['list'] = $user_list;
$user['child_routes']['edit'] = $user_edit;
$user['child_routes']['delete'] = $user_delete;

return array(
    'router' => array(  // 路由器配置
	    'routes' => array(
	        'user'=>$user,	
	    ),
   ),
    'service_manager' => array( // 服务管理配置
        'factories' => array(
            'translator' => 'Zend\I18n\Translator\TranslatorServiceFactory',
        ),
    ),
    'translator' => array(  // 多语言配置
        'locale' => 'en_US',
        'translation_file_patterns' => array(
            array(
                'type'     => 'gettext',
                'base_dir' => __DIR__ . '/../language',
                'pattern'  => '%s.mo',
            ),
        ),
    ),
    'controllers' => array(  // 控制器配置
        'invokables' => array(
            'User\Controller\Index' => 'User\Controller\IndexController'
        ),
    ),
    'view_manager' => array(  //视图管理配置
        'display_not_found_reason' => true,
        'display_exceptions'       => true,
        'doctype'                  => 'HTML5',
        'not_found_template'       => 'error/404',
        'exception_template'       => 'error/index',
        'template_map' => array(
            'layout/layout'           => __DIR__ . '/../view/layout/layout.phtml',
            'user/index/index'        => __DIR__ . '/../view/user/index/index.phtml',
            'error/404'               => __DIR__ . '/../view/error/404.phtml',
            'error/index'             => __DIR__ . '/../view/error/index.phtml',
        ),
        'template_path_stack' => array(
            __DIR__ . '/../view',
        ),
    ),
);


/* ----- old router config -----   */
/*
 *   'router' => array(
        'routes' => array(
            'home' => array(
                'type' => 'Zend\Mvc\Router\Http\Literal',
                'options' => array(
                    'route'    => '/',
                    'defaults' => array(
                        'controller' => 'Application\Controller\Index',
                        'action'     => 'index',
                    ),
                ),
            ),
            // The following is a route to simplify getting started creating
            // new controllers and actions without needing to create a new
            // module. Simply drop new controllers in, and you can access them
            // using the path /application/:controller/:action
            'user' => array(
                'type'    => 'Literal',
                'options' => array(
                    'route'    => '/user',
/*                  'constraints' => array(
                    	'controller' => '[a-zA-Z][a-zA-Z0-9_-]*',
                    	'action'     => '[a-zA-Z][a-zA-Z0-9_-]*',
                    ), */
                  /*  'defaults' => array(
                        '__NAMESPACE__' => 'User\Controller',
                        'controller'    => 'Index',
                        'action'        => 'index',
                    ),
                ),
            ),*/
/*             'user_index' => array(
            		'type'    => 'Literal',
            		'options' => array(
            				'route'    => '/user/index',
            				'defaults' => array(
            						'__NAMESPACE__' => 'User\Controller',
            						'controller'    => 'Index',
            						'action'        => 'index',
            				),
            		),
            ), */
          /*  'login' => array(
            	'type'    => 'Literal',
            	'options' => array(
            		'route'    => '/user/login', 
            		'defaults' => array(
            			'__NAMESPACE__' => 'User\Controller',
            			'controller'    => 'Index',
            			'action'        => 'login',
            		),
            	),
            ),
            'register' => array(
            	'type'    => 'Literal',
            	'options' => array(
            		'route'    => '/user/register',
            		'defaults' => array(
            		'__NAMESPACE__' => 'User\Controller',
            		'controller'    => 'Index',
            		'action'        => 'register',
            	),
            ),
          ),
        ),
    ),
 * 
 * */

