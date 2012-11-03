package library.mapper;

import java.util.List;
import library.domain.Library;
import library.domain.LibraryExample;
import org.apache.ibatis.annotations.Param;

public interface LibraryMapper {
    int countByExample(LibraryExample example);

    int deleteByExample(LibraryExample example);

    int deleteByPrimaryKey(Integer idLibrary);

    int insert(Library record);

    int insertSelective(Library record);

    List<Library> selectByExample(LibraryExample example);

    Library selectByPrimaryKey(Integer idLibrary);
    
    int selectBiggestId();

    int updateByExampleSelective(@Param("record") Library record, @Param("example") LibraryExample example);

    int updateByExample(@Param("record") Library record, @Param("example") LibraryExample example);

    int updateByPrimaryKeySelective(Library record);

    int updateByPrimaryKey(Library record);
}