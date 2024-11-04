using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Task320.Models
{
    [Table("tbl_News")]
    public class News : BaseModel
    {
        [Required]
        [MinLength(2)]
        [MaxLength(64)]
        [Column(TypeName = "text")]
        public string Title { get; set; }

        [Required]
        [MinLength(4)]
        [MaxLength(2048)]
        [Column(TypeName = "text")]
        public string Content { get; set; }

        [Required]
        [DataType(DataType.DateTime)]
        public DateTime Created { get; set; }

        [Required]
        [DataType(DataType.DateTime)]
        public DateTime Modified { get; set; }

        public long CreatorId { get; set; }
        public virtual Creator Creator { get; set; }

        public virtual ICollection<Note> Notes { get; set; } = [];

        public virtual ICollection<Sticker> Stickers { get; set; } = [];
    }
}
