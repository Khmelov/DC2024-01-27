using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Task320.Models
{
    [Table("tbl_Note")]
    public class Note : BaseModel
    {
        [Required]
        [MinLength(2)]
        [MaxLength(2048)]
        [Column(TypeName = "text")]
        public string Content { get; set; }

        public long NewsId { get; set; }
        public virtual News News { get; set; }
    }
}
