<?php
/**
 * Zend Framework (http://framework.zend.com/)
 *
 * @link      http://github.com/zendframework/ZendSkeletonApplication for the canonical source repository
 * @copyright Copyright (c) 2005-2013 Zend Technologies USA Inc. (http://www.zend.com)
 * @license   http://framework.zend.com/license/new-bsd New BSD License
 */

namespace User\Controller;

use Zend\Mvc\Controller\AbstractActionController;
use Zend\View\Model\ViewModel;

class IndexController extends AbstractActionController
{
	protected $usersTable;
	
    public function indexAction(){
       echo __METHOD__;
    }
    
    public function loginAction(){
       echo __METHOD__;
    }
    
    public function registerAction(){
       echo __METHOD__;
    }
    
    public function listAction(){    	
    	$paginator = $this->getUsersTable()->fetchAll();
    	$view = new ViewModel();
    	$view->setTemplate('user/index/list.phtml');
    	$view->setVariable('paginator', $paginator);
    	return $view;
    	    	
/*    	$paginator = $this->getUsersTable()->fetchAll();
    	var_dump($paginator);
    	exit; */  	 
    }
    
    public function editAction(){
    	$view = new ViewModel();
    	$view->setTemplate('user/index/edit.phtml');
    	$view->setVariable('edit', 'edit something');
    	$view->setVariable('ok', 'edit ok');
    	return $view;
    }
    
    public function deleteAction(){
    	$view = new ViewModel();
    	$view->setTemplate('user/index/delete.phtml');
    	$view->setVariable('delete', 'delete something');
    	$view->setVariable('ok', 'delete ok');
    	return $view;
    }
    
    public function getUsersTable(){
    	$register_name = 'User\Model\UsersTable';
    	if(!$this->usersTable){
    		$sm = $this->getServiceLocator();
    		$this->usersTable = $sm->get($register_name);
    	}
    	return $this->usersTable;
    }
    
    
}