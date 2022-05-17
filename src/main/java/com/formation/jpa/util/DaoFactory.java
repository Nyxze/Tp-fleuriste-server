package com.formation.jpa.util;

import com.formation.jpa.dal.BouquetDao;
import com.formation.jpa.dal.BouquetDaoImpl;
import com.formation.jpa.dal.FleurDao;
import com.formation.jpa.dal.FleurDaoImpl;
import com.formation.jpa.dal.PlanteDao;
import com.formation.jpa.dal.PlanteDaoImpl;
import com.formation.jpa.dal.SaisonDao;
import com.formation.jpa.dal.SaisonDaoImpl;
import com.formation.jpa.dal.ShoppingCartDao;
import com.formation.jpa.dal.ShoppingCartDaoImpl;
import com.formation.jpa.dal.StyleDao;
import com.formation.jpa.dal.StyleDaoImpl;

public class DaoFactory {

	public static StyleDao getStyleDao(){
		return new StyleDaoImpl();
	}
	public static SaisonDao getSaisonDao(){
		return new SaisonDaoImpl();
	}
	public static FleurDao getFleurDao(){
		return new FleurDaoImpl();
	}
	public static PlanteDao getPlanteDao(){
		return new PlanteDaoImpl();
	}
	public static BouquetDao getBouquetDao(){
		return new BouquetDaoImpl();
	}

	public static ShoppingCartDao getShoppingCartDao(){
		return new ShoppingCartDaoImpl();
	}
}
