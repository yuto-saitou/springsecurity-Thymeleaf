package com.example.login.infrastructure.database.repository

import com.example.login.domain.model.User
import com.example.login.domain.repository.UserRepository
import com.example.login.infrastructure.database.mapper.UserDynamicSqlSupport
import com.example.login.infrastructure.database.mapper.UserMapper
import com.example.login.infrastructure.database.mapper.selectOne
import com.example.login.infrastructure.database.mapper.insert
import com.example.login.infrastructure.database.record.UserRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.springframework.stereotype.Repository
import org.mybatis.dynamic.sql.SqlBuilder.select
import org.mybatis.dynamic.sql.render.RenderingStrategy

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class UserRepositoryImpl(
    private val mapper: UserMapper
    ) : UserRepository {//入力されたemailとデータベースのemailが同じものを探してrecordに

        override fun find(email: String): User?{
            val record = mapper.selectOne{
                    where(UserDynamicSqlSupport.User.email, isEqualTo(email))
            }
            return record?.let { toModel(it)}
        }

        override fun findAll(): List<User>? {//
            //selectStatementがデータベースのセレクト文(selectとfromとwhereも指定できる)
            val selectStatement = select(UserDynamicSqlSupport.User.allColumns())
                                    .from(UserDynamicSqlSupport.User).build().render(RenderingStrategy.MYBATIS3)
            //上記で指定した部分をselectMany関数(全件検索)の引数に渡してuserrecordlistに代入
            val userrecordlist = mapper.selectMany(selectStatement)
            //
            return userrecordlist?.map { toModel(it) }
        }


        override fun register(user: User){
            val userstatus = mapper.insert(toUserstatus(user))

            return userstatus?.let { toModel()}
        }

    private fun toUserstatus(model:User):UserRecord{
        return UserRecord(model.id,model.email,model.password,model.name,model.roleType)
    }

    //selectManyの戻り値はUserlist型なのでUser型に戻す必要がある。以下のtoModelで実装
    private fun toModel(record: UserRecord): User{
        return User(
            record.id!!,
            record.email!!,
            record.password!!,
            record.name!!,
            record.roleType!!
        )
    }
}