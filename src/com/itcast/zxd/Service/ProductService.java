package com.itcast.zxd.Service;


import com.itcast.zxd.domain.Notice;
import com.itcast.zxd.domain.Product;
import com.itcast.zxd.utils.DButil;

import java.util.ArrayList;
import java.util.List;

/**
 * ������Ʒ�Ļ�������
 * 1.������ҳ�Ĳ�����Ʒ��Ϣ�Ĵ���
 * 2.����ȫ����Ʒ��Ϣ�Ĵ���
 * 3.���ڹ������Ĵ���
 */
public class ProductService {
    //��ѯһ�������ϼܵ���Ʒ��Ϊ��ҳ�Ƽ���Ʒ
    private static String newProductsQuery = " SELECT Proid,ProductName,Author,Description,Imgurl FROM Product ORDER BY Proid DESC LIMIT 1";
    //��ѯ���������������ݣ�����ʱ���������
    private static String noticeString = "SELECT Title,Detail FROM notice ORDER BY Createtime DESC LIMIT 4";
    //��ѯ���е���Ʒ��¼�����ݷ����id������в��ң�
    private static String selectAllproduct1 = "SELECT  Proid,Author,ProductName,Imgurl  FROM product WHERE CategoryId=?";
    //��ѯ���е���Ʒ��¼ ��Ĭ�ϲ���������
    private static String selectAllproduct2 = "SELECT  Proid,Author,ProductName,Imgurl  FROM product";
    //��ѯָ������Ʒ��¼
    private static String CheckDetailProduct = "SELECT Proid,Author,ProductName,Price,Description,Imgurl FROM product WHERE Proid = ? ";
    //Ĭ�ϲ�ѯ��������Ʒ������
    private static String SelectProductCount1 = "SELECT count(*) FROM product";
    private static String SelectProductCount2 = "SELECT count(*) FROM product WHERE CategoryId=?";


    /**
     * Ĭ��չʾ���ݱ�����ӵ���������Ʒ
     *
     * @return �����ϼܵ���Ʒ
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
     * ��ѯָ������Ʒ
     *
     * @param proId ��Ʒ��id����
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
     * ���ҳ����е���Ʒ��¼
     * 2018.6.3���£����Զ���ָ������Ʒ���в�ѯ����
     *
     * @param ��Ʒ�б����������
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
     * Ĭ������²�ѯ�����е���Ʒ������
     *
     * @return ������Ʒ��������
     */
    public long SelectProductCount() {
        List count = DButil.excuteQuery(SelectProductCount1, null);
        Object[] obj = (Object[]) count.get(0);
        long proNumcount = Long.parseLong(obj[0].toString());
        return proNumcount;
    }

    /**
     * ����ĳһ������֮�µ�������Ʒ��ȫ����¼��Ŀ
     */
    public long SelectProductCount(String CategoryId) {
        List count = DButil.excuteQuery(SelectProductCount2, new String[]{CategoryId});
        Object[] obj = (Object[]) count.get(0);
        long proNumcount = Long.parseLong(obj[0].toString());
        return proNumcount;
    }

    /**
     * ��ҳ�������еļ�¼��Ϣ
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
     * ���ҳ����е���Ʒ��¼
     * ��ʹ��������ѯ��ֱ�Ӳ�����������
     *
     * @param ��Ʒ�б����������
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
     * ����ָ����Ŀ�����������
     *
     * @param limit ��ȡ�ĵڼ���
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
     * ����һ�����������ҷ���
     *
     * @param �������ļ���
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
