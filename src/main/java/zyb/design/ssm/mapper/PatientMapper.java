package zyb.design.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import zyb.design.ssm.entity.Patient;

@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
    @Select({
            "<script>",
            "select * from patient",
            "<where>",
            "<if test='patientID != null'>and patientID = #{patientID}</if>",
            "<if test='patientName != null'>and patientName like concat('%', #{patientName}, '%')</if>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "patientID", column = "patientID"),
            @Result(property = "patientName", column = "patientName"),
    })
    Page<Patient> selectPatientByPage(@Param("patientID") Integer patientID,
                                     @Param("patientName") String patientName);
}
