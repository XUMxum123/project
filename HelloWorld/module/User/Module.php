<?php
/**
 * Zend Framework (http://framework.zend.com/)
 *
 * @link      http://github.com/zendframework/ZendSkeletonApplication for the canonical source repository
 * @copyright Copyright (c) 2005-2013 Zend Technologies USA Inc. (http://www.zend.com)
 * @license   http://framework.zend.com/license/new-bsd New BSD License
 */

namespace User;

use Zend\Mvc\ModuleRouteListener;
use Zend\Mvc\MvcEvent;
use Zend\Db\ResultSet\ResultSet;
use Zend\Db\TableGateway\TableGateway;
use User\Model\UsersTable;
use User\Model\Users;

class Module
{
    public function onBootstrap(MvcEvent $e)
    {
        $eventManager        = $e->getApplication()->getEventManager();
        $moduleRouteListener = new ModuleRouteListener();
        $moduleRouteListener->attach($eventManager);
    }

    public function getConfig()
    {
        return include __DIR__ . '/config/module.config.php';
    }

    public function getAutoloaderConfig()
    {
        return array(
            'Zend\Loader\StandardAutoloader' => array(
                'namespaces' => array(
                    __NAMESPACE__ => __DIR__ . '/src/' . __NAMESPACE__,
                ),
            ),
        );
    }
    
    public function getServiceConfig(){
    	return array(
    		'factories'=>array(
    			 'User\Model\UsersTable'=>function($sm){
    					$tg = $sm->get('UsersTableGateway');
    					$table = new UsersTable($tg);
    					return $table;
    				},
    			  'UsersTableGateway'=>function($sm){
    					$adapter = $sm->get('Zend\Db\Adapter\Adapter');
    					$rs = new ResultSet();
    					$rs->setArrayObjectPrototype(new Users());
    					return new TableGateway('users',$adapter,null,$rs);
    				}
    		),
    	);
    }
    
}
