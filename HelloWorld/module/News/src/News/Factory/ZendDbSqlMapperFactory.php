<?php

namespace News\Factory;

use News\Mapper\ZendDbSqlMapper;
use News\Model\post;
use Zend\ServiceManager\FactoryInterface;
use Zend\ServiceManager\ServiceLocatorInterface;
use Zend\Stdlib\Hydrator\ClassMethods;

class ZendDbSqlMapperFactory implements FactoryInterface{
	/**
	 * Create service
	 *
	 * @param ServiceLocatorInterface $serviceLocator
	 *
	 * @return mixed
	 */
	public function createService(ServiceLocatorInterface $serviceLocator)
	{
		return new ZendDbSqlMapper(
				$serviceLocator->get('Zend\Db\Adapter\Adapter'),
				new ClassMethods(false),
				new post()
		);
	}
	
}

?>