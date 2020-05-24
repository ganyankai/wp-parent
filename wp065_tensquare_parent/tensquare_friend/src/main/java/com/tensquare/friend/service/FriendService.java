package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;


    public int addFriend(String userId, String friendid) {
        //先判断userid到friendid是否有数据,有就是重复添加好友,返回0
        Friend friend = friendDao.findByUseridAndFriendid(userId, friendid);
        if (friend!=null){
            return 0;
        }
        //直接添加好友,让好友表中userid到friendid方向的type为0
        friend = new Friend();
        friend.setUserid(userId);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断从friendid到userid是否有数据,如果有,把双方的状态都改为1
        if (friendDao.findByUseridAndFriendid(friendid,userId) != null){
            //把双方的islike都改为1
            friendDao.updateIslike("1",userId,friendid);
            friendDao.updateIslike("1",friendid,userId);
        }
        return 1;
    }

    public int addNoFriend(String userid, String friendid) {
        //先判断是否已经是非好友
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userid, friendid);
        if (noFriend!=null) return 0;
        noFriend = new NoFriend(userid,friendid);
        noFriendDao.save(noFriend);
//        //判断从friendid到userid是否有数据,如果有,把对方的状态都改为0
//        if (friendDao.findByUseridAndFriendid(friendid,userid) != null){
//
//            //把双方的islike都改为0
//            friendDao.updateIslike("0",userid,friendid);
//
//        }
        return 1;
    }

    public void deleteFriend(String userid, String friendid) {
        //删除好友表中userid到friendid这条记录
        friendDao.deleteFriend(userid,friendid);
        //更新从friendid到userid的islike为0
        friendDao.updateIslike("0",friendid,userid);
        //非好友表中添加数据
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
}
