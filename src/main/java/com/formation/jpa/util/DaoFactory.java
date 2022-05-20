package com.formation.jpa.util;

import com.formation.jpa.dal.BouquetDao;
import com.formation.jpa.dal.BouquetDaoImpl;
import com.formation.jpa.dal.FleurDao;
import com.formation.jpa.dal.FleurDaoImpl;
import com.formation.jpa.dal.PlanteDao;
import com.formation.jpa.dal.PlanteDaoImpl;
import com.formation.jpa.dal.ProductDao;
import com.formation.jpa.dal.ProductDaoImpl;
import com.formation.jpa.dal.SaisonDao;
import com.formation.jpa.dal.SaisonDaoImpl;
import com.formation.jpa.dal.ShoppingCartItemDao;
import com.formation.jpa.dal.ShoppingCartItemDaoImpl;
import com.formation.jpa.dal.StyleDao;
import com.formation.jpa.dal.StyleDaoImpl;
import com.formation.jpa.dal.UserDao;
import com.formation.jpa.dal.UserDaoImpl;

public class DaoFactory {

	public static StyleDao getStyleDao() {
		return new StyleDaoImpl();
	}

	public static SaisonDao getSaisonDao() {
		return new SaisonDaoImpl();
	}

	public static FleurDao getFleurDao() {
		return new FleurDaoImpl();
	}

	public static PlanteDao getPlanteDao() {
		return new PlanteDaoImpl();
	}

	public static BouquetDao getBouquetDao() {
		return new BouquetDaoImpl();
	}

	public static ShoppingCartItemDao getShoppingCartItemDao() {
		return new ShoppingCartItemDaoImpl();
	}

	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}
	public static ProductDao getProductDao() {
		return new ProductDaoImpl();
	}
}
