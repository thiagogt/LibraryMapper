package test;

import library.domain.Bookshelf;
import library.domain.Node;

import org.junit.Test;

public class BookShelfTest {
	@Test
	public void cacthVersionIdTest(){
		Bookshelf bookshelf = new Bookshelf();
		bookshelf.cacthVersionId("uuwbfg9999");
		
	}
	@Test
	public void foundFirstNumberPositionFrom(){
		Bookshelf bookshelf = new Bookshelf();
		
		String xml = "QA700.RT I59c 1992 v.05 e.2";
		String xml2 = "QA700 - Computa��o";
		String xml3 = "REF. QA27.L D752LP";
		String xml4 = "QA340 A958m";
		System.out.println(bookshelf.foundDivisionBetweenPrefixAndVersionId(xml));
		System.out.println(bookshelf.foundDivisionBetweenPrefixAndVersionId(xml2));
		System.out.println(bookshelf.foundDivisionBetweenPrefixAndVersionId(xml3));
		System.out.println(bookshelf.foundDivisionBetweenPrefixAndVersionId(xml4));
		}
	
	@Test
	public void createInitialShelfIndetificationTest(){
		Bookshelf bookshelf = new Bookshelf();
		
		String xml = "QA700.RT I59c 1992 v.05 e.2";
		String xml2 = "QA700 - Computa��o";
		String xml3 = "REF. QA27.L D752LP";
		String xml4 = "QA340 A958m";
		
		bookshelf.createInitialShelfIndetification(xml);
		System.out.println(xml);
		System.out.println(bookshelf.getPrefixCodeIdInitial());
		System.out.println(bookshelf.getCodeIdInitial());
		System.out.println(bookshelf.getVersionCodeIdInitial());
		System.out.println();
		
		bookshelf.createInitialShelfIndetification(xml2);
		System.out.println(xml2);
		System.out.println(bookshelf.getPrefixCodeIdInitial());
		System.out.println(bookshelf.getCodeIdInitial());
		System.out.println(bookshelf.getVersionCodeIdInitial());
		System.out.println();
		
		bookshelf.createInitialShelfIndetification(xml3);
		System.out.println(xml3);
		System.out.println(bookshelf.getPrefixCodeIdInitial());
		System.out.println(bookshelf.getCodeIdInitial());
		System.out.println(bookshelf.getVersionCodeIdInitial());
		System.out.println();
		
		bookshelf.createInitialShelfIndetification(xml4);
		System.out.println(xml4);
		System.out.println(bookshelf.getPrefixCodeIdInitial());
		System.out.println(bookshelf.getCodeIdInitial());
		System.out.println(bookshelf.getVersionCodeIdInitial());
		System.out.println();
	}
	@Test
	public void returnNodeOfShelfTest(){
		Bookshelf bookshelf = new Bookshelf();
		bookshelf.setIdLibrary(2);
		
		String initial = "preI1 verI1";
		String finaly = "pref1 verf1";
		bookshelf.createInitialShelfIndetification(initial);
		bookshelf.createFinalShelfIndetification(finaly);
		
		Node node = bookshelf.returnNodeOfShelf();
	}
//	
//	<select id="selectByCodeIdShelf" resultMap="BaseResultMap" >
//  	select 
//    <include refid="Base_Column_List" />
//  	from public.bookshelf
//    where id_library = #{idLibrary,jdbcType=INTEGER},
//  	prefix_code_id_initial = #{prefixCodeIdInitial,jdbcType=VARCHAR} AND
//	prefix_code_id_final = #{prefixCodeIdFinal,jdbcType=VARCHAR} AND
//	code_id_initial &lt; #{codeIdInitial,jdbcType=INTEGER} AND 
//	code_id_final &gt; #{codeIdFinal,jdbcType=INTEGER}
//  </select>
	
}
