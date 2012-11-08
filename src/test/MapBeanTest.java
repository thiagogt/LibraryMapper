package test;

import library.bean.MapBean;

import org.junit.Test;

public class MapBeanTest {

	
	@Test
	public void cathInitialPartTest(){
		MapBean mapBean = new MapBean();
		System.out.println(mapBean.cathInitialPart("olaaaa**vc"));
	}
	@Test
	public void cathFinalPartTest(){
		MapBean mapBean = new MapBean();
		System.out.println(mapBean.catchFinalPart("olaaaa**vc"));
	}	
}
