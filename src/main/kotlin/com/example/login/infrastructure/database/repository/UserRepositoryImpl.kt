package com.example.login.infrastructure.database.repository

import com.example.login.domain.model.User
import com.example.login.domain.repository.UserRepository
import com.example.login.infrastructure.database.mapper.UserDynamicSqlSupport
import com.example.login.infrastructure.database.mapper.UserMapper
import com.example.login.infrastructure.database.mapper.selectOne
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
            val selectStatement = select(UserDynamicSqlSupport.User.allColumns())
                                    .from(UserDynamicSqlSupport.User).build().render(RenderingStrategy.MYBATIS3)
            val userrecordlist = mapper.selectMany(selectStatement)
            return userrecordlist?.map { toModel(it) }
        }

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