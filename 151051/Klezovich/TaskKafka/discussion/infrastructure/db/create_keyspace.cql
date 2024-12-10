CREATE KEYSPACE IF NOT EXISTS distcomp
    WITH REPLICATION = {
        'class' : 'NetworkTopologyStrategy',
        'datacenter1' : 1
        };