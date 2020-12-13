package service;

import domain.Impress;
import domain.ImpressDetail;

import java.util.List;

public interface ImpressService {

    /**
     * 添加一个好友印象
     * @param impress 好友印象
     */
    public void addImpress(Impress impress);

    /**
     * 更新好友印象
     * @param impress 好友印象
     */
    public void updateImpress(Impress impress);

    /**
     * 删除一个好友印象
     * @param impressId 好友印象id
     */
    public void deleteImpress(Long impressId);

    /**
     * 获得其它所有人对该用户的印象（n条）
     * @param uid 用户id
     * @param n n条
     * @return
     */
    public List<ImpressDetail> getImpresses(Long uid, Integer n);


    /**
     * 获取 uid2 对 uid1 的印象
     * @param uid1 用户1的uid
     * @param uid2 用户2的uid
     * @return 好友印象
     */
    public ImpressDetail getImpress(Long uid1, Long uid2);
}
