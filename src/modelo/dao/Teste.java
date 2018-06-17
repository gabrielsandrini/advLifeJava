package modelo.dao;

import java.util.ArrayList;

import modelo.pojo.Mata;

public class Teste {
	
	public static void main(String[] args) {
		MataDAO mataDao = new MataDAO();
		ArrayList<Mata> matas = mataDao.consultarMatas();
		System.out.println(matas);
	}
}
