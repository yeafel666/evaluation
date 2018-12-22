package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.Index;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *  指标dao
 * Created by kangyifan on 2018/10/8 9:46
 */
public interface IndexRepository extends JpaRepository<Index,Long> {


    @Transactional
    @Query(value = "select * from `index` where if(?1 !='',index_name=?1,1=1)",nativeQuery = true)
    Page<Index> findIndexIfIndexNameIsNotNull(String indexName, Pageable pageable);


    @Transactional
    @Query(value = "select count(*) from `index` where if(?1 !='',index_name=?1,1=1)",nativeQuery = true)
    Integer countIndexForPage(String indexName);


    /** 通过id查询指标 .
     * !!!!!!!!!!!!!!!!!注意，这里因为我的表名是index,是数据库的关键词，所以这里的查询方法我手动写sql语句。
     * 以后表或者字段的命名我应该尽量避开数据库关键词。   关键词用``包起来，Tab上面的键。
     * */
//    @Query(value = "select * from `index` where index_Id = ? ", nativeQuery = true)

    /**  重大更新！！即使表名为关键词也不需要改表名就能够使用jpa自动查询，如果是表名就 @Table(name = "[Index]")
     *   如果是列名关键词冲突，就在列上加@Column(name = "[indexId]"),  简直舒服得一匹！！
     * @param indexId
     * @return
     */
    Index findByIndexId(Long indexId);


    void deleteByIndexId(Long indexId);







}
