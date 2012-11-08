package library.mapper;

import java.util.List;
import library.domain.Bookshelf;
import library.domain.BookshelfExample;
import library.domain.Node;

import org.apache.ibatis.annotations.Param;

public interface BookshelfMapper {
    int countByExample(BookshelfExample example);

    int deleteByExample(BookshelfExample example);

    int deleteByPrimaryKey(Integer idBookshelf);
    
    int deleteByLibraryId(Integer idLibrary);

    int insert(Bookshelf record);

    int insertSelective(Bookshelf record);
    
    int selectBiggestId();
    Node selectByPositionXAndY(@Param("idLibrary") Integer idNode,@Param("positionY") Integer positionY,@Param("positionX") Integer positionX);
    List<Bookshelf> selectByCodeIdShelf(Bookshelf bookshelf);
    
    List<Bookshelf> selectByExample(BookshelfExample example);

    Bookshelf selectByPrimaryKey(Integer idBookshelf);

    int updateByExampleSelective(@Param("record") Bookshelf record, @Param("example") BookshelfExample example);

    int updateByExample(@Param("record") Bookshelf record, @Param("example") BookshelfExample example);

    int updateByPrimaryKeySelective(Bookshelf record);

    int updateByPrimaryKey(Bookshelf record);
}