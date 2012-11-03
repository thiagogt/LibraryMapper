package library.mapper;

import java.util.List;

import library.domain.Bookshelf;
import library.domain.Node;
import library.domain.NodeExample;
import org.apache.ibatis.annotations.Param;

public interface NodeMapper {
    int countByExample(NodeExample example);

    int deleteByExample(NodeExample example);

    int deleteByPrimaryKey(Integer idNode);

    int deleteByLibraryId(Integer idLibrary);	
    
    int insert(Node shelf);

    int insertSelective(Node record);
    
    int selectBiggestId();

    List<Node> selectByExample(NodeExample example);

    Node selectByPrimaryKey(Integer idNode);

    Node selectByPositionXAndY(@Param("idLibrary") Integer idNode,@Param("positionX") Integer positionX,@Param("positionY") Integer positionY);
    
    int updateByExampleSelective(@Param("record") Node record, @Param("example") NodeExample example);

    int updateByExample(@Param("record") Node record, @Param("example") NodeExample example);

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKey(Node record);
    
    
}