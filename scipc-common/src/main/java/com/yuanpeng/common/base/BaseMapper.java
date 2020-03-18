package com.yuanpeng.common.base;

import java.util.List;

/**
 * @description: 基础的Mapper
 * @author: YuanPeng
 * @create: 2020-02-11 16:12
 */
public interface BaseMapper<D, E> {

    /**
     *  DTO -> Entity
     * @param dto
     * @return
     */
    E toEntity(D dto);

    /**
     * Entity -> DTO
     * @param entity
     * @return
     */
    D toDto(E entity);

    /**
     * DTO集合转Entity集合
     * @param dtoList /
     * @return /
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Entity集合转DTO集合
     * @param entityList /
     * @return /
     */
    List <D> toDto(List<E> entityList);
}
