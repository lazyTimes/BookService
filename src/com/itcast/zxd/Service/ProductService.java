package com.itcast.zxd.Service;


import com.itcast.zxd.domain.Notice;
import com.itcast.zxd.domain.Product;
import com.itcast.zxd.utils.DButil;

import java.util.ArrayList;
import java.util.List;

/**
 * 对于商品的基本处理
 * 1.对于首页的部分商品信息的处理
 * 2.对于全部商品信息的处理
 * 3.对于公告栏的处理
 */
public class ProductService {
    //查询一个最新上架的商品作为首页推荐商品
    private static String newProductsQuery = " SELECT Proid,ProductName,Author,Description,Imgurl FROM Product ORDER BY Proid DESC LIMIT 1";
    //查询公告栏的所有内容（根据时间进行排序）
    private static String noticeString = "SELECT Title,Detail FROM notice ORDER BY Createtime DESC LIMIT 4";
    //查询所有的商品记录（根据分类的id号码进行查找）
    private static String selectAllproduct1 = "SELECT  Proid,Author,ProductName,Imgurl  FROM product WHERE CategoryId=?";
    //查询所有的商品记录 （默认查找条件）
    private static String selectAllproduct2 = "SELECT  Proid,Author,ProductName,Imgurl  FROM product";
    //查询指定的商品记录
    private static String CheckDetailProduct = "SELECT Proid,Author,ProductName,Price,Description,Imgurl FROM product WHERE Proid = ? ";
    //默认查询的所有商品的总数
    private static String SelectProductCount1 = "SELECT count(*) FROM product";
    private static String SelectProductCount2 = "SELECT count(*) FROM product WHERE CategoryId=?";


    /**
     * 默认展示数据表中添加的三个新商品
     *
     * @return 最新上架的商品
     */
    public Product newProducts() {

        Product pro = new Product();
        List pojo = DButil.excuteQuery(newProductsQuery, null);
        for (int x = 0; x < pojo.size(); x++) {
            Object[] obj = (Object[]) pojo.get(x);
            pro.setProid(obj[0].toString());
            pro.setProductName(obj[1].toString());
            pro.setAuthor(obj[2].toString());
            pro.setDescription(obj[3].toString());
            pro.setImgurl(obj[4].toString());
        }

        return pro;

    }

    /**
     * 查询指定的商品
     *
     * @param proId 商品的id号码
     */
    public Product selectOneProduct(String proId) {
        Product pro = new Product();
        List pojo = DButil.excuteQuery(CheckDetailProduct, new String[]{proId});

        Object[] obj = (Object[]) pojo.get(0);
        pro.setProid(obj[0].toString());
        pro.setAuthor(obj[1].toString());
        pro.setProductName(obj[2].toString());
        pro.setPrice(Double.parseDouble(obj[3].toString()));
        pro.setDescription(obj[4].toString());
        pro.setImgurl(obj[5].toString());
        pro.setPcount(1);
        return pro;

    }


    /**
     * 查找出所有的商品记录
     * 2018.6.3更新，可以对于指定的商品进行查询操作
     *
     * @param 商品列表的所有内容
     */
    public List<Product> selectAll(String CategoryId) {
        List<Product> list = new ArrayList<>();
        List pojo = DButil.excuteQuery(selectAllproduct1, new String[]{CategoryId});
        for (int x = 0; x < pojo.size(); x++) {
            Object[] obj = (Object[]) pojo.get(x);
            Product product = new Product();
            product.setProid(obj[0].toString());
            product.setAuthor(obj[1].toString());
            product.setProductName(obj[2].toString());
            product.setImgurl(obj[3].toString());
            list.add(product);
        }
        return list;

    }

    /**
     * 默认情况下查询出所有的商品的数量
     *
     * @return 所有商品的总数量
     */
    public long SelectProductCount() {
        List count = DButil.excuteQuery(SelectProductCount1, null);
        Object[] obj = (Object[]) count.get(0);
        long proNumcount = Long.parseLong(obj[0].toString());
        return proNumcount;
    }

    /**
     * 查找某一个分类之下的所有商品的全部记录数目
     */
    public long SelectProductCount(String CategoryId) {
        List count = DButil.excuteQuery(SelectProductCount2, new String[]{CategoryId});
        Object[] obj = (Object[]) count.get(0);
        long proNumcount = Long.parseLong(obj[0].toString());
        return proNumcount;
    }

    /**
     * 分页查找所有的记录信息
     *
     * @param start
     * @param total
     * @return
     */
    public List<Product> findAll(Integer start, Integer total) {
        String selectAllInfo = "SELECT Proid,ProductName,Pnum,Price,CategoryId FROM product LIMIT " + ((start - 1) * total) + "," + total;
        System.out.println(selectAllInfo);
        List list = DButil.excuteQuery(selectAllInfo, null);
        List<Product> result = new ArrayList<>();
        for (int x = 0; x < list.size(); x++) {
            Object[] obj = (Object[]) list.get(x);
            Product product = new Product();
            product.setProid(obj[0].toString());
            product.setProductName(obj[1].toString());
            product.setPnum(Integer.parseInt(obj[2].toString()));
            product.setPrice(Double.parseDouble(obj[3].toString()));
            product.setCategoryId(Integer.parseInt(obj[4].toString()));
            ;
            result.add(product);
        }
        return result;
    }

    /**
     * 查找出所有的商品记录
     * 不使用条件查询，直接查找所有内容
     *
     * @param 商品列表的所有内容
     */
    public List<Product> selectAll() {
        List<Product> list = new ArrayList<>();
        List pojo = DButil.excuteQuery(selectAllproduct2, null);
        for (int x = 0; x < pojo.size(); x++) {
            Object[] obj = (Object[]) pojo.get(x);
            Product product = new Product();
            product.setProid(obj[0].toString());
            product.setAuthor(obj[1].toString());
            product.setProductName(obj[2].toString());
            product.setImgurl(obj[3].toString());
            list.add(product);
        }
        return list;

    }


    /**
     * 根据指定条目进行随机查找
     *
     * @param limit 截取的第几条
     */
    public Product RandomSelect(String limit) {

        String RandomSelect = "SELECT Proid,ProductName,Author,Description,Imgurl "
                + "FROM Product ORDER BY Proid DESC LIMIT " + limit + ",1";
        Product pro = new Product();
        List pojo = DButil.excuteQuery(RandomSelect, null);
        Object[] obj = (Object[]) pojo.get(0);
        pro.setProid(obj[0].toString());
        pro.setProductName(obj[1].toString());
        pro.setAuthor(obj[2].toString());
        pro.setDescription(obj[3].toString());
        pro.setImgurl(obj[4].toString());
        return pro;
    }


    /**
     * 创建一个公告板对象并且返回
     *
     * @param 公告栏的集合
     */
    public List<Notice> CreateNotice() {
        List<Notice> list = new ArrayList<>();

        List pojo = DButil.excuteQuery(noticeString, null);
        for (int x = 0; x < pojo.size(); x++) {
            Object[] obj = (Object[]) pojo.get(x);
            Notice notice = new Notice(obj[0].toString(), obj[1].toString());
            list.add(notice);
        }
        return list;
    }

    public void add(Product product) {
        String queryString = "INSERT INTO product VALUES (?,?,?,?,?,?,?,?)";
        boolean res = DButil.excuteUpdateString(queryString, new String[]{product.getProid(), product.getAuthor(), product.getProductName(),
                product.getPnum() + "", product.getPrice() + "", product.getDescription(),
                product.getImgurl(), product.getCategoryId() + ""});


    }
}
