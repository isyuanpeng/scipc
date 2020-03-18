package com.yuanpeng.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 作业分数Dto
 * @author: YuanPeng
 * @create: 2020-02-14 17:45
 */
@Data
public class HomeworkScoreDto implements Serializable {

    private Long id;

    /** 作业id */
    private HomeworkDto homework;

    /** 老师id */
    private TeacherDto teacher;

    /** 分数 */
    private String score;

    /** 评语 */
    private String remark;

}