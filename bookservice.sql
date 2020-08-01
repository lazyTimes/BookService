/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : bookservice

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2020-08-01 23:02:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `1234561cart`
-- ----------------------------
DROP TABLE IF EXISTS `1234561cart`;
CREATE TABLE `1234561cart` (
  `CartId` int(11) NOT NULL AUTO_INCREMENT,
  `Proid` varchar(100) NOT NULL,
  `Userid` int(11) NOT NULL,
  `pcount` int(11) NOT NULL DEFAULT '1',
  `UpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CartId`),
  UNIQUE KEY `Proid` (`Proid`),
  KEY `Userid` (`Userid`),
  CONSTRAINT `1234561cart_ibfk_1` FOREIGN KEY (`Proid`) REFERENCES `product` (`Proid`),
  CONSTRAINT `1234561cart_ibfk_2` FOREIGN KEY (`Userid`) REFERENCES `user` (`Userid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 1234561cart
-- ----------------------------

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `N_id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) DEFAULT NULL,
  `Detail` varchar(255) DEFAULT NULL,
  `Createtime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`N_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for `orderitems`
-- ----------------------------
DROP TABLE IF EXISTS `orderitems`;
CREATE TABLE `orderitems` (
  `Orderid` varchar(255) NOT NULL,
  `Proid` varchar(255) DEFAULT NULL,
  `Onumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitems
-- ----------------------------
INSERT INTO `orderitems` VALUES ('d727c87b07ed4c7f85c50fb408410660', '6552e59f-279b-4507-a769-3dd403da1a48', '1');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `Orderid` varchar(255) NOT NULL,
  `Userid` int(11) DEFAULT NULL,
  `OrderName` varchar(255) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `Receivename` varchar(255) DEFAULT NULL,
  `ReceivePhone` varchar(255) DEFAULT NULL,
  `ReceiveAddress` varchar(255) DEFAULT NULL,
  `OrderCreatetime` varchar(255) DEFAULT NULL,
  `PayState` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`Orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('d727c87b07ed4c7f85c50fb408410660', '2', '1234561?2020-08-01 21:13:22???????', '1', '2', '2', '2', '2020-08-01 21:13:22', '1');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `Proid` varchar(255) NOT NULL,
  `Author` varchar(255) DEFAULT NULL,
  `ProductName` varchar(255) DEFAULT NULL,
  `Pnum` int(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `CategoryId` int(11) DEFAULT NULL,
  `Imgurl` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`Proid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('6552e59f-279b-4507-a769-3dd403da1a48', '1', '??', '14', '1.0', '1', 'resource/productImg/none.png', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Gender` int(11) DEFAULT NULL,
  `Activecode` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Status` int(11) DEFAULT '1',
  `Telephone` varchar(255) DEFAULT NULL,
  `Regist_Time` varchar(255) DEFAULT NULL,
  `Role` int(11) DEFAULT NULL,
  PRIMARY KEY (`Userid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123456', '123456', '0', 'TRSVETHSBOTBFAICHQGUIGKSWJSC', '1097483508@qq.com', '1', '18229357812', '2020-08-01 20:03:43', '1');
INSERT INTO `user` VALUES ('2', '1234561', '123456', '0', 'BVDOCNWTRAQRNAVTTOASCKJWOTOT', '1097483508@qq.com', '1', '18229357812', '2020-08-01 20:31:20', '0');
