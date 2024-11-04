
using Cassandra.Mapping.Attributes;

namespace Task340.Discussion.Models
{
    [System.ComponentModel.DataAnnotations.Schema.Table("tbl_Note")]
    public class Note
    {
        [ClusteringKey]
        public long Id { get; set; }
        [ClusteringKey]
        public long NewsId { get; set; }
        public string Content { get; set; }
    }
}
