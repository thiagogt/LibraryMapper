package library.mapper;

import java.util.List;
import library.domain.QrCodeMark;
import library.domain.QrCodeMarkExample;
import org.apache.ibatis.annotations.Param;

public interface QrCodeMarkMapper {
    int countByExample(QrCodeMarkExample example);

    int deleteByExample(QrCodeMarkExample example);

    int deleteByPrimaryKey(Integer idQrMark);

    int insert(QrCodeMark record);

    int insertSelective(QrCodeMark record);

    List<QrCodeMark> selectByExample(QrCodeMarkExample example);

    QrCodeMark selectByPrimaryKey(Integer idQrMark);

    int updateByExampleSelective(@Param("record") QrCodeMark record, @Param("example") QrCodeMarkExample example);

    int updateByExample(@Param("record") QrCodeMark record, @Param("example") QrCodeMarkExample example);

    int updateByPrimaryKeySelective(QrCodeMark record);

    int updateByPrimaryKey(QrCodeMark record);
}