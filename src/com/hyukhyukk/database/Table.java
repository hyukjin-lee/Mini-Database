package com.hyukhyukk.database;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public interface Table extends Serializable, Cloneable {
    Object clone() throws CloneNotSupportedException;

    String name();

    void rename(String newName);

    boolean isDirty();

    int insert(String[] columnNames, Object[] values);

    int insert(Collection columnNames, Collection values);

    int insert(Object[] values);

    int insert(Collection values);

    int update(Selector where);

    int delete(Selector where);

    void begin();

    void commit(boolean all) throws IllegalStateException;

    void rollback(boolean all) throws IllegalStateException;

    Table select(Selector where, String[] requestedColumns, Table[] other);

    Table select(Selector where, String[] requestedColumns);

    Table select(Selector where);

    Table select(Selector where, Collection requestedColumns, Collection other);

    Table select(Selector where, Collection requestedColumns);

    Cursor rows();

    void export(Table.Exporter importer) throws IOException;

    interface Exporter {
        void startTable() throws IOException;

        void storeMetadata(String tableName, int width, int height, Iterator columnNames) throws IOException;

        void storeRow(Iterator data) throws IOException;

        void endTable() throws IOException;
    }

    interface Importer {
        void startTable() throws IOException;

        String loadTableName() throws IOException;

        int loadWidth() throws IOException;

        Iterator loadColumnNames() throws IOException;

        Iterator loadRow() throws IOException;

        void endTable() throws IOException;
    }
}
