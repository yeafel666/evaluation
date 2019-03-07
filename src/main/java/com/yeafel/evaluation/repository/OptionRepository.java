package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kangyifan on 2018/10/9 14:00
 */
public interface OptionRepository extends JpaRepository<Option,Long> {

    /** 查询某个指标的选项 .*/
    List<Option> findOptionsByIndexId(Long indexId);


    @Transactional
    @Query(value = "select count(*) from `option` where if(?1 !='',index_id=?1,1=1)",nativeQuery = true)
    Integer countOptionsForPage(Long indexId);


    /** 分页查询某个指标的选项 .*/
    Page<Option> findOptionsByIndexId(Long indexId, Pageable pageable);


    Option findOptionByOptionId(Long optionId);


    void deleteByOptionId(Long optionId);



}
