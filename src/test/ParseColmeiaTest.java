package  test;



import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import library.domain.Node;
import library.mapper.NodeMapper;
import library.parser.ParseColmeiaXML;
import library.utils.SQLFactory;

import org.junit.Test;
import org.xml.sax.SAXException;

public class ParseColmeiaTest {

	@Test
	public void testExtractBookList() throws ParserConfigurationException, SAXException, IOException{
		String xml = "<response><resultados><obras><total>8</total><livro><id_obra>22007</id_obra><titulo>Proyecto de una matriz de componentes</titulo><subtitulos/><autores>"+
                  "<autor>Diaz, Pedro Cuervo</autor><autor>Lopez, Alvaro Gustavo</autor><autor>Kestelboim, Augusto Gabariel</autor>"+
            "</autores><editoras><editora>EBAI</editora></editoras><volume></volume><assuntos><assunto>QA732.2 - Arquitetura e Organiza��o de Computadores &gt; Componentes e Circuitos</assunto></assuntos>"+
            "<ano_edicao>1993</ano_edicao><eventos><evento>Escola Brasileiro-Argentina de Inform�tica, 6�, Cordoba, AR, 1993</evento>"+
            "</eventos><exemplares><exemplar><numero>1</numero><chamada>QA732.2 D542p e.1</chamada></exemplar><exemplar><numero>2</numero>"+
                       "<chamada>QA732.2 D542p e.2</chamada></exemplar></exemplares><status><id_status>2</id_status>"+
            "</status></livro></obras></resultados></response>";

		ParseColmeiaXML.extractBookList(xml);
	}

}