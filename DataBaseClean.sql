DO $$
    BEGIN
        EXECUTE (
            SELECT string_agg(format('DROP TABLE IF EXISTS %I CASCADE;', tablename), ' ')
            FROM pg_tables
            WHERE schemaname = 'public'
        );
END $$;
