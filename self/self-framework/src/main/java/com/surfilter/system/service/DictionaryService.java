/**
 * Project Name:smcs<br>
 * File Name:DictionaryService.java<br>
 * Package Name:com.smcs.framework.system.service<br>
 * Date:2013年09月18日  上午10:50:16<br>
 *
*/
package com.surfilter.system.service;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surfilter.framework.page.Page;
import com.surfilter.system.dao.DictionaryMapper;
import com.surfilter.system.model.Dictionary;

/**
 * ClassName:DictionaryService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月18日  上午10:50:16<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class DictionaryService {

	/**
	 *注入dictionaryMapper
	 */
	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author hongcheng
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<Dictionary> getPageModel(Dictionary entity,RowBounds rowBounds){
		Page<Dictionary> page = new Page<Dictionary>();
		long total = dictionaryMapper.count(entity);
		List<Dictionary> rows = dictionaryMapper.list(entity,rowBounds);
		
		page = new Page<Dictionary>(total, rows);
		return page;
	}
	
	/**
	 * getPageList:查询List.
	 *
	 * @author zhangwei
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<Dictionary> getPageList(Dictionary entity){
		List<Dictionary> rows = dictionaryMapper.list(entity);
		return rows;
	}
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Dictionary entity){
		dictionaryMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(Dictionary entity){
		return dictionaryMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Dictionary getEntityById(long id){
		return dictionaryMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id){
		
		return dictionaryMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author hongcheng
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids){
    	return dictionaryMapper.delBatchEntity(ids);
    }
    
    /**
     * 
     * getDictionaryByType:根据字典类型获取字典数据. <br/>
     *
     * @author wangguohong
     * @param type
     * @return
     * @since JDK 1.6
     */
    public List<Dictionary> getDictionaryByType(String type){
    	return dictionaryMapper.getDictionaryByType(type);
    }
    
    /**
     * 
     * getDictionaryByKey:根据type ，key 获取字典数据. <br/>
     *
     * @author wangguohong
     * @param type
     * @param key
     * @return
     * @since JDK 1.6
     */
    public Dictionary getDictionaryByKey(String type,String key){
    	return dictionaryMapper.getDictionaryByKey(type,key);
    }

    /**
     * getTreeGridAllDic: 获取所有的子节点,已树形关系存储
     * getTreeGridAllDic:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author tangbiao
     * @param dic
     * @return
     * @since JDK 1.6
     */
	public Dictionary getTreeGridAllDic(Dictionary dic, long parentId, String codeKey) {
		// 组装查询条件，使用父节点查询所有的子节点
		Dictionary queryDic = new Dictionary();
		queryDic.setParentId(parentId);
		queryDic.setCodeKey(codeKey);
		List<Dictionary> dics = dictionaryMapper.list(queryDic);
		
		if(dics != null && dics.size()>0){
			for (Dictionary dictionary : dics) {
				if(dictionary.getParentId() != null){
					Dictionary queryDicByTmp = new Dictionary();
					queryDicByTmp.setParentId(dictionary.getId());
					List<Dictionary> dicsTmp = dictionaryMapper.list(queryDicByTmp);
					if(dicsTmp != null && dicsTmp.size()>0){
						getTreeGridAllDic(dictionary, dictionary.getId(), codeKey);
					}
				}else{
					break;
				}
			}
			dic.setChildren(dics);
		}
		return dic;
	}

	/**
	 * 
	 * getTreeGridByParentId:异步树通过父节点获取子节点. <br/>
	 *
	 * @author tangbiao
	 * @param parentId
	 * @return
	 * @since JDK 1.6
	 */
	public List<Dictionary> getTreeGridByParentId(Long parentId) {
		// 组装查询条件，使用父节点查询所有的子节点
		Dictionary queryDic = new Dictionary();
		queryDic.setParentId(parentId);
		List<Dictionary> dics = dictionaryMapper.list(queryDic);
		if(dics != null && dics.size()>0){
			for (Dictionary dictionary : dics) {
				if(dictionary.getParentId() != null){
					Dictionary queryDicByTmp = new Dictionary();
					queryDicByTmp.setParentId(dictionary.getId());
					List<Dictionary> dicsTmp = dictionaryMapper.list(queryDicByTmp);
					if(dicsTmp != null && dicsTmp.size()>0){
						dictionary.setState("closed");
					}else{
						dictionary.setState(null);
					}
				}
			}
		}
		return dics;
	}
	
}
